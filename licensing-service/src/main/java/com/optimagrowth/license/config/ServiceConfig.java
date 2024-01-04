package com.optimagrowth.license.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
@Setter
public class ServiceConfig{

  @Value("${example.property}")
  private String property;
    
  @Value("${redis.server}")
  private String redisServer="";

  @Value("${redis.port}")
  private String redisPort="";
  
}