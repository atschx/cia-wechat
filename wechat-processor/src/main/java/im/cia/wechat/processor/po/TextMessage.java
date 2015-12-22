package im.cia.wechat.processor.po;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class TextMessage extends Message {


	@XStreamAlias("Content")
	private String content;
	@XStreamAlias("MsgId")
	private String msgId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
