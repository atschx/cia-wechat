package im.cia.wechat.processor.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageUtilTest {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Test
	public void testBuildTextMessage() {
		logger.debug(MessageUtil.buildTextMessage("1212", "wx_asdasd", ""));
	}

}
