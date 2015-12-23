package im.cia.wechat.processor.domain.model;

/**
 * 用户与公众号的状态。
 */
public class UserStatus extends UserWeChat {

	private boolean hasSubscribed;

	/**
	 * 是否已关注。1，已关注 0，未关注
	 * 
	 * @return
	 */
	public boolean getHasSubscribed() {
		return hasSubscribed;
	}

	public void setHasSubscribed(boolean hasSubscribed) {
		this.hasSubscribed = hasSubscribed;
	}

}
