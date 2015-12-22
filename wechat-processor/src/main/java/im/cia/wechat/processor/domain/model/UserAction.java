package im.cia.wechat.processor.domain.model;

/**
 * 用户通过公众号与服务器交互的行为,用于备查信息。
 */
public class UserAction extends UserWeChat {

	private String msgType;

	/**
	 * 用户的行为分类及公众号交互的消息类型。
	 * 
	 * @return
	 */
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}
