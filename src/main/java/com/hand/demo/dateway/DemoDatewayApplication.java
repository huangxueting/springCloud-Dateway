package com.hand.demo.dateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class DemoDatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDatewayApplication.class, args);
	}

}
