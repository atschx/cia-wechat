package im.cia.wechat.processor.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import im.cia.wechat.processor.utils.CheckSignatureUtil;
import im.cia.wechat.processor.utils.MessageUtil;

@WebServlet(name = "wechat", urlPatterns = "/*")
public class WeChatServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(WeChatServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		if (CheckSignatureUtil.checkSignature(signature, timestamp, nonce)) {
			PrintWriter out = response.getWriter();
			out.println(echostr);
			out.close();
			out = null;
		} else {
			PrintWriter out = response.getWriter();
			out.println("");
			out.close();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		PrintWriter out = response.getWriter();

		if (CheckSignatureUtil.checkSignature(signature, timestamp, nonce)) {
			try {
				Map<String, String> map = MessageUtil.xmlToMap(request);
				String fromUserName = map.get("FromUserName");
				String toUserName = map.get("ToUserName");
				String msgType = map.get("MsgType");
				String content = map.get("Content");
				LOGGER.debug("FromUserName:" + fromUserName + "->ToUserName:" + toUserName + "-:" + content);
				// !解析xml->
				String message = "";
				// 文本类消息
				if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
					if ("?".equals(content.trim())) {
						message = MessageUtil.buildTextMessage(fromUserName, toUserName, MessageUtil.menuText());
					}
				} else if (MessageUtil.MESSAGE_EVNET.equals(msgType)) {
					String eventType = map.get("Event");
					if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
						message = MessageUtil.buildTextMessage(fromUserName, toUserName, MessageUtil.menuText());
					} else if (MessageUtil.MESSAGE_CLICK.equals(eventType)) {
						message = MessageUtil.buildTextMessage(fromUserName, toUserName, MessageUtil.menuText());
					}
				} else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)) {
					// 接收用户地理位置信息
				}
				out.print(message);
			} catch (Exception e) {
				LOGGER.error("处理数据失败", e);
				out.println("");
			} finally {
				try {
					if (out != null) {
						out.close();
						out = null;
					}
				} catch (Exception e2) {
					// NO－ops.
				}
			}
		} else {
			try {
				out.println("");
				out.close();
			} catch (Exception e) {
				// NO－ops.
			}
		}
	}
}
