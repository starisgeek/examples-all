package com.starisgeek.examples.springmvc.initializer;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.starisgeek.examples.springmvc.configuration.MvcConfiguration;

/**
 * @author Administrator
 * @see org.springframework.web.WebApplicationInitializer
 */
public class DefaultDispatcherServletInitializer
		extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { MvcConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/**
	 * 这里可以配置Filter
	 */
	@Override
	protected Filter[] getServletFilters() {
		return super.getServletFilters();
	}

}
