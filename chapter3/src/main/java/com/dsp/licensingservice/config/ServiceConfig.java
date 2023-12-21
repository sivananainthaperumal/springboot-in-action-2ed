package com.dsp.licensingservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "example")
@Configuration
public class ServiceConfig {

    private String property;

    public String getProperty(){
        return property;
    }

    public void setProperty(String property){
        this.property=property;
    }
}
