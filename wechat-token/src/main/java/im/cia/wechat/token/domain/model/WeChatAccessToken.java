package im.cia.wechat.token.domain.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wechat_access_token")
public class WeChatAccessToken {

	@Id
	@Column(name = "app_id")
	private String appId;

	@Column(name = "app_secret", nullable = false)
	private String appSecret;

	@Column(name = "access_token", length = 512)
	private String accessToken;

	@Column(name = "expired_at")
	private Timestamp expiredAt;

	@Column(name = "update_time")
	private Timestamp updateTime;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Timestamp getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Timestamp expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
