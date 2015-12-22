package im.cia.wechat.processor.service.handler;

/**
 * 统一处理微信交互性消息
 */
public interface MessageHandler {

	/**
	 * 对外只需要暴露一个方法即可。
	 * @param messageBody
	 * @return
	 */
	String handler(String messageBody);

}
