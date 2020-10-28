/**
 * Author : chizf
 * Date : 2020年10月27日 下午4:37:42
 * Title : org.fms.rs.web.WebAppConfig.java
 *
**/
package org.fms.rs.web;

import org.fms.rs.web.interceptor.BaseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new BaseInterceptor()).addPathPatterns("/**");
	}
}