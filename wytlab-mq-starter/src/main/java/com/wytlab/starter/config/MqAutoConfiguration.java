package com.wytlab.starter.config;

import com.wytlab.starter.properties.MrMqProperties;
import com.wytlab.starter.service.MqServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MrMqProperties.class)
@ConditionalOnClass(MqServiceConfiguration.class)
@ConditionalOnProperty(prefix = "com.mq", value = "enabled", matchIfMissing = true)
public class MqAutoConfiguration {

    @Autowired
    private MrMqProperties mrMqProperties;

    @Bean
    @ConditionalOnMissingBean(MqServiceConfiguration.class)
    public MqServiceConfiguration mqService() {
        return new MqServiceConfiguration(mrMqProperties.getAddress(), mrMqProperties.getChannel());
    }

}
