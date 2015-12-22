package im.cia.wechat.processor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import im.cia.wechat.processor.servlet.WeChatServlet;
import im.cia.wechat.processor.utils.CheckSignatureUtil;

@Controller
public class WeChatController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WeChatServlet.class);

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "I'm working!";
	}

	@RequestMapping(value="/wechat", method= RequestMethod.POST)
	@ResponseBody
	String wechat(
			@RequestParam(value = "signature", required = true) String signature,
			@RequestParam(value = "timestamp", required = false) String timestamp,
			@RequestParam(value = "nonce", required = false) String nonce, 
			@RequestBody String body) {

		String message = "";
		if (CheckSignatureUtil.checkSignature(signature, timestamp, nonce)){
			LOGGER.debug(body);
			
			//原样返回
			message = body;
		}
		
		return message;
	}
	
	/**
	 * 校验入口，通过直接返回 echostr
	 * @param signature
	 * @param timestamp
	 * @param echostr
	 * @param nonce
	 * @return
	 */
	@RequestMapping(value="/wechat", method= RequestMethod.GET)
	@ResponseBody
	String checkSignature(
			@RequestParam(value = "signature", required = true) String signature,
			@RequestParam(value = "timestamp", required = false) String timestamp,
			@RequestParam(value = "echostr", required = false) String echostr, 
			@RequestParam(value = "nonce", required = false) String nonce
			) {
		
		if (CheckSignatureUtil.checkSignature(signature, timestamp, nonce)){
			return echostr;
		}
		
		return "";
	}

}
