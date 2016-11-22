package com.brandon.configurations;

import java.util.List;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.brandon.BlogApplication;
import com.brandon.BlogConstants;
import com.brandon.configurations.xss.HTMLCharacterEscapes;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by brandon Lee on 2016-10-26.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = BlogApplication.class)
public class MvcConfiguration extends WebMvcConfigurerAdapter implements BlogConstants {
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/js/**").addResourceLocations("classpath:/static/javascript/");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
        // JSON XSS Filter
		//objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
		//converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource());
		return localValidatorFactoryBean;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(pageableHandlerMethodArgumentResolver());
	}

	public PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver() {
		PageableHandlerMethodArgumentResolver pageableHandlerMethodArgumentResolver = new PageableHandlerMethodArgumentResolver();
		pageableHandlerMethodArgumentResolver.setOneIndexedParameters(true);
		return pageableHandlerMethodArgumentResolver;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setCacheSeconds(-1);
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setDefaultEncoding(CHARACTER_ENCODING.name());
		return messageSource;
	}

	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(LocaleContextHolder.getLocale());
		localeResolver.setDefaultTimeZone(TimeZone.getDefault());
		localeResolver.setCookieName("locale");
		localeResolver.setCookieMaxAge(-1);
		return localeResolver;
	}

	@Override
	public Validator getValidator() {
		return localValidatorFactoryBean();
	}
}
