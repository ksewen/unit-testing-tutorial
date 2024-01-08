package com.github.ksewen.unit.testing.tutorial.conditional.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

/**
 * @author ksewen
 * @date 08.01.2024 18:17
 */
class PropertyConditionalTest {

  @Test
  void conditionalOnPropertyMarker() {
    new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(PropertyConditional.class))
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(PropertyConditional.Marker.class);
              assertThat(context)
                  .getBean("conditionalOnPropertyMarker")
                  .isSameAs(context.getBean(PropertyConditional.Marker.class));
            });
  }

  @Test
  void noConditionalOnPropertyMarker() {
    new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(PropertyConditional.class))
        .withPropertyValues("conditional.enabled=false")
        .run(
            (context) -> {
              assertThat(context).doesNotHaveBean(PropertyConditional.Marker.class);
            });
  }
}
