package im.cia.wechat.processor.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户通过公众号与服务器交互的行为,用于备查信息。
 */
@Entity
public class UserAction extends UserWeChat {
	
	private String id;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", length = 11)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
