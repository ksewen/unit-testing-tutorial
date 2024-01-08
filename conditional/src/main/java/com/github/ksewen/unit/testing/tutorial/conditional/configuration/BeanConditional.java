package com.github.ksewen.unit.testing.tutorial.conditional.configuration;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ksewen
 * @date 08.01.2024 13:38
 */
@Configuration
public class BeanConditional {

  @Bean
  @ConditionalOnBean(Logger.class)
  public Marker conditionalOnBeanMarker() {
    return new Marker();
  }

  @Bean
  @ConditionalOnMissingBean(Marker.class)
  public Marker conditionalOnMissingBeanMarker() {
    return new Marker();
  }

  static class Marker {}
}
