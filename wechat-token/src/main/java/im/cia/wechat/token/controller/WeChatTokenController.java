package im.cia.wechat.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import im.cia.wechat.token.service.TokenService;

@Controller
public class WeChatTokenController {
	
	@Autowired
	TokenService tokenService;
	
	//提供appid获取对应的token
	@RequestMapping(value = "/wechat", method = RequestMethod.GET)
	@ResponseBody
	String token(
			@RequestParam(value = "appid") String appid) {


		return "";
	}
	

}
