package com.starisgeek.examples.springmvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.starisgeek.examples.springmvc.controller" })
public class MvcConfiguration {
	@Bean
	public WebMvcConfigurer defaultWebMvcConfigurer() {
		return new DefaultWebMvcConfigurer();
	}

	/**
	 * @author Administrator
	 * @see org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration.setConfigurers(List<WebMvcConfigurer>)
	 */
	public static class DefaultWebMvcConfigurer implements WebMvcConfigurer {
		@Override
		public void configureViewResolvers(ViewResolverRegistry registry) {
			// registry.viewResolver(new
			// InternalResourceViewResolver("/WEB-INF/pages/", ".jsp"));
			// 创建一个InternalResourceViewResolver实例，并设置prefix和suffex
			registry.jsp("/WEB-INF/pages/", ".jsp");
			// 创建ContentNegotiatingViewResolver实例
			// registry.enableContentNegotiation(new View[0]);
		}
	}
}
