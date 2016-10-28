package com.brandon.configurations;

import com.brandon.BlogConstants;
import com.brandon.properties.BlogSettings;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.boot.autoconfigure.PebbleAutoConfiguration;
import com.mitchellbosecke.pebble.spring4.PebbleViewResolver;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Pebble Auto Configuration 이 동작한 이후에 추가적으로 설정해야하는 것들을 설정해준다
 *
 * Created by brandon Lee on 2016-10-28.
 */
@Configuration
@ConditionalOnClass(PebbleEngine.class)
@AutoConfigureAfter(PebbleAutoConfiguration.class)
@EnableConfigurationProperties(value = {BlogSettings.class})
public class PebbleConfiguration implements BlogConstants {
    private final Logger logger = getLogger(getClass());
    private final ObjectMapper objectMapper;

    public PebbleConfiguration(BlogSettings blogSettings, PebbleViewResolver pebbleViewResolver) {
        this.objectMapper = new ObjectMapper();
        pebbleViewResolver.setAttributesMap(objectToMap(blogSettings));
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
