package com.github.ksewen.unit.testing.tutorial.conditional.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

/**
 * @author ksewen
 * @date 08.01.2024 13:01
 */
class ClassConditionalTest {

  @Test
  void conditionalOnClassMarker() {
    new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(ClassConditional.class))
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(ClassConditional.Marker.class);
              assertThat(context)
                  .getBean("conditionalOnClassMarker")
                  .isSameAs(context.getBean(ClassConditional.Marker.class));
            });
  }

  @Test
  void conditionalOnMissingClassMarker() {
    new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(ClassConditional.class))
        .withClassLoader(new FilteredClassLoader(Logger.class))
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(ClassConditional.Marker.class);
              assertThat(context)
                  .getBean("conditionalOnMissingClassMarker")
                  .isSameAs(context.getBean(ClassConditional.Marker.class));
            });
  }
}
