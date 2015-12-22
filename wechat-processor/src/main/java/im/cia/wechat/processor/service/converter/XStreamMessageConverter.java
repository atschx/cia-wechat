package im.cia.wechat.processor.service.converter;

import com.thoughtworks.xstream.converters.basic.StringConverter;

public class XStreamMessageConverter extends StringConverter {

	@Override
	public String toString(Object obj) {
		return "<![CDATA[" + super.toString(obj) + "]]>";
	}

}
