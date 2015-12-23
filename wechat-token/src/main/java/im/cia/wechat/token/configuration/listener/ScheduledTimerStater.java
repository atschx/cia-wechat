package im.cia.wechat.token.configuration.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;


@Component
public class ScheduledTimerStater implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//启动timer定时获取AccessToken
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
