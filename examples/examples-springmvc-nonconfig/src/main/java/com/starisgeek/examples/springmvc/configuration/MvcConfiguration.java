package com.starisgeek.examples.springmvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
			registry.viewResolver(new InternalResourceViewResolver("/WEB-INF/pages/", ".jsp"));
		}
	}
}
