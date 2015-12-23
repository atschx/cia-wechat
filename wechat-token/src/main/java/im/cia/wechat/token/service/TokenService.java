package im.cia.wechat.token.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;

import im.cia.wechat.token.domain.model.WeChatAccessToken;
import im.cia.wechat.token.domain.repository.WeChatAccessTokenRepository;

@Service
public class TokenService {

	@Autowired
	WeChatAccessTokenRepository accessTokenRepository;

	public String getTokenByAppId(String appId) {
		WeChatAccessToken chatAccessToken = accessTokenRepository.findOne(appId);
		if (chatAccessToken == null) {
			return "";
		} else {
			long offset = (chatAccessToken.getExpiredAt().getTime() - System.currentTimeMillis()) / 1000;
			//预留10分钟的token获取间隔
			if (offset <= 600) {
				//重新主动获取一次Token
				chatAccessToken.getAppId();
				chatAccessToken.getAppSecret();
			}
		}
		return chatAccessToken.getAccessToken();
	}
	
	public static void main(String[] args) {
		
		
		try {
			//基于SSL与微信服务器进行交互获取对应的Token
			TrustManager[] trustManagers = {new X509TrustManager (){
				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}}}; 
			
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE"); 
			sslContext.init(null, trustManagers, new java.security.SecureRandom()); 

			//从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			
			
			String appid="wx3661cbf137c98272";
			String appsecret="e7d8b8a1605be1ea94d87d962d8e18e8";
					
			 String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
			  URL url = new URL(requestUrl);
		      HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
		      httpUrlConn.setSSLSocketFactory(sslSocketFactory);
		      httpUrlConn.setDoOutput(true);
		      httpUrlConn.setDoInput(true);
		      httpUrlConn.setUseCaches(false);
		      httpUrlConn.setRequestMethod("GET");
		      httpUrlConn.connect();
		      
		      InputStream inputStream = httpUrlConn.getInputStream();
		      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		      String str = null;
		      StringBuffer buffer = new StringBuffer();
		      while ((str = bufferedReader.readLine()) != null) {
		        buffer.append(str);
		      }
		      bufferedReader.close();
		      inputStreamReader.close();
		      // 释放资源
		      inputStream.close();
		      inputStream = null;
		      httpUrlConn.disconnect();
		      
		      
//		      System.out.println(buffer.toString());
		      
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
