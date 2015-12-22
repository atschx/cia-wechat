package im.cia.wechat.processor.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import im.cia.wechat.processor.service.XStreamTransformer;
import im.cia.wechat.processor.service.bean.WeChatXmlMessage;

@Service
public class WeChatMessageHandler implements MessageHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeChatMessageHandler.class);

	@Override
	public String handler(String messageBody) {

		// xml->obj
		WeChatXmlMessage weChatMessage = XStreamTransformer.fromXml(WeChatXmlMessage.class, messageBody);

		String toUserName = weChatMessage.getToUserName();
		String fromUserName = weChatMessage.getFromUserName();

		LOGGER.debug("FromUserName:" + fromUserName + "->ToUserName:" + toUserName + "-:");

		// 交换一下,你说啥我回复啥
		weChatMessage.setToUserName(fromUserName);
		weChatMessage.setFromUserName(toUserName);

		// obj->xml
		String replayMessage = XStreamTransformer.toXml(WeChatXmlMessage.class, weChatMessage);
		return replayMessage;

	}

}
