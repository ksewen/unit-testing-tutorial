package com.github.ksewen.unit.testing.tutorial.conditional.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ksewen
 * @date 08.01.2024 18:11
 */
@Configuration
public class PropertyConditional {

  @Bean
  @ConditionalOnProperty(value = "conditional.enabled", havingValue = "true", matchIfMissing = true)
  public Marker conditionalOnPropertyMarker() {
    return new Marker();
  }

  static class Marker {}
}
