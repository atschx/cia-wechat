package im.cia.wechat.processor.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户通过公众号与服务器交互的行为,用于备查信息。
 */
@Entity
@Table(name = "user_action")
public class UserAction extends UserWeChat {

	private String msgType;

	/**
	 * 用户的行为分类及公众号交互的消息类型。
	 * 
	 * @return
	 */
	@Column(name = "msg_type")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}
