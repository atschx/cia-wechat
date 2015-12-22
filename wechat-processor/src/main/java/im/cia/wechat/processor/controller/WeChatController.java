package im.cia.wechat.processor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import im.cia.wechat.processor.service.handler.MessageHandler;
import im.cia.wechat.processor.utils.CheckSignatureUtil;

@Controller
public class WeChatController {

	@Autowired
	private MessageHandler messageHandler;

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "I'm working!";
	}

	/**
	 * 处理用户的交互信息，公众号视消息内容给予反馈。
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param messageBody
	 * @return
	 */
	@RequestMapping(value = "/wechat", method = RequestMethod.POST)
	@ResponseBody
	String wechat(
			@RequestParam(value = "signature", required = true) String signature,
			@RequestParam(value = "timestamp", required = false) String timestamp,
			@RequestParam(value = "nonce", required = false) String nonce, 
			@RequestBody String messageBody) {

		String message = "";
		if (CheckSignatureUtil.checkSignature(signature, timestamp, nonce)) {
			message = messageHandler.handler(messageBody);
		}

		return message;
	}

	/**
	 * 校验入口，通过直接返回 echostr
	 * 
	 * @param signature
	 * @param timestamp
	 * @param echostr
	 * @param nonce
	 * @return
	 */
	@RequestMapping(value = "/wechat", method = RequestMethod.GET)
	@ResponseBody
	String checkSignature(
			@RequestParam(value = "signature", required = true) String signature,
			@RequestParam(value = "timestamp", required = false) String timestamp,
			@RequestParam(value = "echostr", required = false) String echostr,
			@RequestParam(value = "nonce", required = false) String nonce) {

		if (CheckSignatureUtil.checkSignature(signature, timestamp, nonce)) {
			return echostr;
		}

		return "";
	}

}
