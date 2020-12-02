package com.xhx.restclient.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 注册resttemplate
 * 
 * @date 2019年5月23日
 * @author xhx
 */
@Configuration
public class RestConfig {

	@Bean
	@Primary
	public RestTemplate httpRestTemplate() {
		// restTemplate.setMessageConverters(...); 可以添加消息转换
		// restTemplate.setInterceptors(...); 可以增加拦截器
		return new RestTemplate(httpRequestFactory());
	}

	private ClientHttpRequestFactory httpRequestFactory() {
		return new HttpComponentsClientHttpRequestFactory(restTemplateConfigHttpClient());
	}

	/**
	 * 配置httpclient连接池
	 * 
	 * @return
	 */
	private HttpClient restTemplateConfigHttpClient() {
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", SSLConnectionSocketFactory.getSocketFactory()).build();
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
		// 设置整个连接池最大连接数 根据自己的场景决定
		// 后面调整从配置中心获取
		connectionManager.setMaxTotal(50);
		// 路由是对maxTotal的细分
		// 后面调整从配置中心获取
		connectionManager.setDefaultMaxPerRoute(100);
		RequestConfig requestConfig = RequestConfig.custom()
				// 服务器返回数据(response)的时间，超过该时间抛出read timeout
				// 后面调整从配置中心获取
				.setSocketTimeout(10000)
				// 连接上服务器(握手成功)的时间，超出该时间抛出connect timeout
				// 后面调整从配置中心获取
				.setConnectTimeout(5000)
				// 从连接池中获取连接的超时时间，超过该时间未拿到可用连接，
				// 会抛出org.apache.http.conn.ConnectionPoolTimeoutException:
				// Timeout waiting for connection from pool
				// 后面调整从配置中心获取
				.setConnectionRequestTimeout(5000).build();
		return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager)
				.build();
	}
}
