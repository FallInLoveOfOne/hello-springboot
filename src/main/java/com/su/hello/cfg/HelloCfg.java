package com.su.hello.cfg;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@ComponentScan(value = "com.su.hello")
@Import(value = HelloCfg.class)
@ImportResource(value = "classpath:mvc.xml")
public class HelloCfg {
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public RestTemplate getRT() {
		return new RestTemplate();
	}

}
