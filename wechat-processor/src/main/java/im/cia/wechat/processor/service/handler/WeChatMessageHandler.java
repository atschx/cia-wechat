package im.cia.wechat.processor.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import im.cia.wechat.processor.domain.model.UserAction;
import im.cia.wechat.processor.domain.repository.UserActionRepository;
import im.cia.wechat.processor.service.XStreamTransformer;
import im.cia.wechat.processor.service.bean.WeChatXmlMessage;

@Service
public class WeChatMessageHandler implements MessageHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeChatMessageHandler.class);
	
	@Autowired
	private UserActionRepository userActionRepository;

	@Override
	public String handler(String messageBody) {
		WeChatXmlMessage weChatMessage = XStreamTransformer.fromXml(WeChatXmlMessage.class, messageBody);
		String replayMessage  = "";
		String msgType = weChatMessage.getMsgType();
		if ("event".equals(msgType)) {
			//6种事件推送
			replayMessage = eventMessage(weChatMessage);
		}else{
			//7种普通消息
			replayMessage = normalMessage(weChatMessage);
		}
		return replayMessage;
	}

	private String normalMessage(WeChatXmlMessage weChatMessage) {
		
		String toUserName = weChatMessage.getToUserName();
		String fromUserName = weChatMessage.getFromUserName();
		String msgType = weChatMessage.getMsgType();
		
		
		// 普通消息
//		|| "text".equals(msgType) 
//		|| "image".equals(msgType) 
//		|| "voice".equals(msgType) 
//		|| "video".equals(msgType)
//		|| "shortvideo".equals(msgType) 
//		|| "location".equals(msgType) 
//		|| "link".equals(msgType)) 
		
		String replayMessage  = "";
		
		if("text".equals(msgType) ){
			LOGGER.debug(weChatMessage.getMsgId() + "-FromUserName:" + fromUserName + "->ToUserName:" + toUserName + "-:"
					+ msgType + ":" + weChatMessage.getContent());

			// 交换一下,你说啥我回复啥
			weChatMessage.setToUserName(fromUserName);
			weChatMessage.setFromUserName(toUserName);
			replayMessage = XStreamTransformer.toXml(WeChatXmlMessage.class, weChatMessage);
		}
		
		if("image".equals(msgType) ){
			LOGGER.debug(weChatMessage.getMsgId() + "-FromUserName:" + fromUserName + "->ToUserName:" + toUserName
					+ "-:" + msgType + ":"+ weChatMessage.getPicUrl()+ weChatMessage.getMediaId());
		}
		

		// 2.此处依据事件类型进行不同的处理。

		
		return replayMessage;
	}
	
	private String eventMessage(WeChatXmlMessage weChatMessage) {

		String toUserName = weChatMessage.getToUserName();
		String fromUserName = weChatMessage.getFromUserName();
		String msgType = weChatMessage.getMsgType();
		String event = weChatMessage.getEvent();

		LOGGER.debug(weChatMessage.getMsgId() + "-FromUserName:" + fromUserName + "->ToUserName:" + toUserName + "-:"
				+ msgType + ":" + event);

		String replayMessage = "";
		// 关注
		if ("subscribe".equals(event)) {
			
			UserAction userAction = new UserAction();
			userAction.setAppId(toUserName);
			userAction.setOpenId(fromUserName);
			userAction.setMsgType(msgType);
			UserAction userActionFromDb = userActionRepository.save(userAction);
			LOGGER.debug(userActionFromDb.getId());
		}

		// 取消关注
		if ("unsubscribe".equals(event)) {

		}

		// 扫描二维码
		if ("SCAN".equals(event)) {

		}

		// 上报地理位置
		if ("LOCATION".equals(event)) {

		}

		// 点击菜单拉取消息时的事件推送
		if ("CLICK".equals(event)) {

		}

		// 点击菜单跳转链接时的事件推送
		if ("VIEW".equals(event)) {

		}

		return replayMessage;
	}
}
