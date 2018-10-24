
package com.empathy.utils.wx;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpUtil {
	
	/**
	 * 最大链接数
	 */
	private static final int MAXTOTAL=200;
	/**
	 * 设置每个主机地址的并发数
	 */
	private static final int DEFAULTMAXPERROUTE=20;
	/**
	 * 创建连接的最长时间
	 */
	private static final int CONNECTTIMWOUT=5000;
	/**
	 * 数据传输的最长时间
	 */
	private static final int SOCKETTIMEOUT=10000;
	/**
	 * 从连接池中获取到连接的最长时间
	 */
	private static final int CONNECTIONREQUESTTIMEOUT=1000;
	
	private static CloseableHttpClient httpClient=null;
	
	static{
		PoolingHttpClientConnectionManager poolManagger=new PoolingHttpClientConnectionManager();
		poolManagger.setMaxTotal(MAXTOTAL);
		poolManagger.setDefaultMaxPerRoute(DEFAULTMAXPERROUTE);
		RequestConfig config= RequestConfig.custom()
				.setConnectTimeout(CONNECTTIMWOUT)
				.setSocketTimeout(SOCKETTIMEOUT)
				.setConnectionRequestTimeout(CONNECTIONREQUESTTIMEOUT).build();
	
	httpClient= HttpClientBuilder.create().setConnectionManager(poolManagger).setDefaultRequestConfig(config).build();
	//	HttpClientBuilder.create().setConnectionManager(poolManagger)
	}
	
	public static CloseableHttpClient getHttpClient(){
		return httpClient;
	}
	
}
