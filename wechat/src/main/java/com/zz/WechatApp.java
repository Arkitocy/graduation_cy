package com.zz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 右键--》run as application  运行正启动类的main方法，就可以启动这个springboot项目。
SpringBoot r自带了 tomcat， 运行这个main方法 的时候，会同时启动tomcat
 * @author jiyu
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class WechatApp {

	@Bean
	@LoadBalanced
	public  RestTemplate restTemplate() {

		return new RestTemplate();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(WechatApp.class, args);
	}

}
