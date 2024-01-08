package com.github.ksewen.unit.testing.tutorial.conditional.configuration;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ksewen
 * @date 08.01.2024 12:57
 */
@Configuration
public class ClassConditional {

  @Bean
  @ConditionalOnClass(Logger.class)
  public Marker conditionalOnClassMarker() {
    return new Marker();
  }

  @Bean
  @ConditionalOnMissingClass("org.apache.logging.log4j.Logger")
  public Marker conditionalOnMissingClassMarker() {
    return new Marker();
  }

  static class Marker {}
}
