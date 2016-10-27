package com.brandon.configurations;

import com.brandon.BlogApplication;
import com.brandon.properties.BlogSettings;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.Loader;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.spring4.PebbleViewResolver;
import com.mitchellbosecke.pebble.spring4.extension.SpringExtension;
import org.slf4j.Logger;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by brandon Lee on 2016-10-26.
 */
@Configuration
@EnableWebMvc
@EnableConfigurationProperties(BlogSettings.class)
@ComponentScan(basePackageClasses = BlogApplication.class)
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    private final Logger logger = getLogger(getClass());
    private final ServletContext servletContext;
    private final BlogSettings blogSettings;
    private final ObjectMapper objectMapper;
    public MvcConfiguration(ServletContext servletContext, BlogSettings blogSettings) {
        this.servletContext = servletContext;
        this.blogSettings = blogSettings;
        this.objectMapper = new ObjectMapper().setDefaultPrettyPrinter(new DefaultPrettyPrinter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/js/**").addResourceLocations("classpath:/static/javascript/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Bean
    public PebbleEngine pebbleEngine() {
        return new PebbleEngine.Builder().loader(this.templateLoader()).extension(this.springExtension()).build();
    }

    @Bean
    public SpringExtension springExtension() {
        return new SpringExtension();
    }

    @Bean
    public Loader<?> templateLoader() {
        return new ServletLoader(this.servletContext);
    }

    @Bean
    public ViewResolver viewResolver() {
        PebbleViewResolver viewResolver = new PebbleViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");
        viewResolver.setPebbleEngine(this.pebbleEngine());
        viewResolver.setAttributesMap(objectToMap(blogSettings));
        return viewResolver;
    }

    private Map<String, ?> objectToMap(Object object) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(object), new TypeReference<Map>() {
            });
        } catch (Exception e) {
            logger.warn("Can't convert Object to Properties!!");
            return new HashMap<>();
        }
    }
}
