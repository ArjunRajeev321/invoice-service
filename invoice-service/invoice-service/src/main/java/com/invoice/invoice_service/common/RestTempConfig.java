package com.invoice.invoice_service.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTempConfig {
	
	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
