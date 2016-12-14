package com.bluebird.config;

import java.util.HashMap;

import javax.faces.webapp.FacesServlet;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.bluebird")
@EntityScan(basePackages = "com.bluebird")
public class ApplicationJSF extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationJSF.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationJSF.class);
	}

	  @SuppressWarnings("serial")
		@Bean public org.springframework.beans.factory.config.CustomScopeConfigurer customScopeConfigurer(){
	    	CustomScopeConfigurer csc = new CustomScopeConfigurer();
	    	csc.setScopes(new HashMap<String, Object>() {{
	    		put("view", new com.bluebird.config.ViewScope());
	    	}});
	    	return csc;
	    }

	    @Bean
	    public ServletRegistrationBean servletRegistrationBean() {
	        FacesServlet servlet = new FacesServlet();
	        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.xhtml", "*.jsf");
			return servletRegistrationBean;
	    }
}