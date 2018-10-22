package com.starisgeek.examples.springmvc.configuration;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.ResourceRegionHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.starisgeek.examples.springmvc.controller" })
public class MvcConfiguration implements WebMvcConfigurer {
	private ApplicationContext applicationContext;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(
				StandardCharsets.UTF_8);
		stringHttpMessageConverter.setWriteAcceptCharset(false); // see SPR-7316

		messageConverters.add(new ByteArrayHttpMessageConverter());
		messageConverters.add(stringHttpMessageConverter);
		messageConverters.add(new ResourceHttpMessageConverter());
		messageConverters.add(new ResourceRegionHttpMessageConverter());
		messageConverters.add(new SourceHttpMessageConverter<>());
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
		Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
		if (this.applicationContext != null) {
			builder.applicationContext(this.applicationContext);
		}
		messageConverters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// 创建一个InternalResourceViewResolver实例，并设置prefix和suffex
		registry.jsp("/WEB-INF/pages/", ".jsp");
		// 创建ContentNegotiatingViewResolver实例。enableContentNegotiation方法参数不能传null!!!
		// registry.enableContentNegotiation(new View[0]);
	}
}
