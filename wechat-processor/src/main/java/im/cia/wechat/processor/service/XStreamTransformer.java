package im.cia.wechat.processor.service;

import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

import im.cia.wechat.processor.service.bean.WeChatXmlMessage;

public class XStreamTransformer {

	private static final Map<Class<?>, XStream> CLASS_2_XSTREAM_INSTANCE = configXStreamInstance();

	private static Map<Class<?>, XStream> configXStreamInstance() {
		Map<Class<?>, XStream> map = new HashMap<Class<?>, XStream>();
		map.put(WeChatXmlMessage.class, configWeChatXmlMessage());
		return map;
	}

	private static XStream configWeChatXmlMessage() {
		XStream xstream = XStreamInitializer.getInstance();
		xstream.processAnnotations(WeChatXmlMessage.class);
		xstream.processAnnotations(WeChatXmlMessage.ScanCodeInfo.class);
		xstream.processAnnotations(WeChatXmlMessage.SendPicsInfo.class);
		xstream.processAnnotations(WeChatXmlMessage.SendPicsInfo.Item.class);
		xstream.processAnnotations(WeChatXmlMessage.SendLocationInfo.class);
		xstream.aliasField("MsgID", WeChatXmlMessage.class, "msgId");
		return xstream;
	}

	/**
	 * 从微信接收的数据，转换为结构化对象。
	 * @param clazz
	 * @param xml
	 * @return
	 */
	public static <T> T fromXml(Class<T> clazz, String xml) {
		@SuppressWarnings("unchecked")
		T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(xml);
		return object;
	}

	/**
	 * 将原始数据转换为微信可识别的回复结构。
	 * @param clazz
	 * @param object
	 * @return
	 */
	public static <T> String toXml(Class<T> clazz, T object) {
		return CLASS_2_XSTREAM_INSTANCE.get(clazz).toXML(object);
	}
}
