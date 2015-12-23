package im.cia.wechat.processor.domain.model;

import javax.persistence.MappedSuperclass;

/**
 * 
 * 对公众号而言 用户仅仅表现为 公众号及openId
 * 
 */
@MappedSuperclass
public class UserWeChat {

	private String appId;// 公众号
	private String openId;// 针对公众号的微信号标识
	private String unionId;// 针对一个企业系列的微信号可通过unionId进行互通

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

}
