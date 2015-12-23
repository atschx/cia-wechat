package im.cia.wechat.processor.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户与公众号的状态。
 */
@Entity
@Table(name = "user_status")
public class UserStatus extends UserWeChat {

	private boolean hasSubscribed;

	/**
	 * 是否已关注。1，已关注 0，未关注
	 * 
	 * @return
	 */
	@Column(name = "has_subscribed")
	public boolean getHasSubscribed() {
		return hasSubscribed;
	}

	public void setHasSubscribed(boolean hasSubscribed) {
		this.hasSubscribed = hasSubscribed;
	}

}
