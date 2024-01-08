package com.github.ksewen.unit.testing.tutorial.conditional.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.internal.util.MockUtil.isMock;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ksewen
 * @date 08.01.2024 13:39
 */
class BeanConditionalTest {

  @Test
  void conditionalOnBeanMarker() {
    new ApplicationContextRunner()
        .withConfiguration(
            AutoConfigurations.of(BeanConditional.class, MockLoggerAutoConfiguration.class))
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(BeanConditional.Marker.class);
              assertThat(context)
                  .getBean("conditionalOnBeanMarker")
                  .isSameAs(context.getBean(BeanConditional.Marker.class));
            });
  }

  @Test
  void conditionalOnMissingBeanMarker() {
    new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(BeanConditional.class))
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(BeanConditional.Marker.class);
              assertThat(context)
                  .getBean("conditionalOnMissingBeanMarker")
                  .isSameAs(context.getBean(BeanConditional.Marker.class));
              assertThat(isMock(context.getBean(BeanConditional.Marker.class))).isFalse();
            });
  }

  @Test
  void conditionalOnMissingBeanWithMockMarker() {
    new ApplicationContextRunner()
        .withConfiguration(
            AutoConfigurations.of(BeanConditional.class, MockMarkerAutoConfiguration.class))
        .run(
            (context) -> {
              assertThat(context).hasSingleBean(BeanConditional.Marker.class);
              assertThat(context)
                  .getBean("mockMarker")
                  .isSameAs(context.getBean(BeanConditional.Marker.class));
              assertThat(isMock(context.getBean(BeanConditional.Marker.class))).isTrue();
            });
  }

  @Configuration
  @AutoConfigureBefore(BeanConditional.class)
  static class MockLoggerAutoConfiguration {

    @Bean
    public Logger logger() {
      return mock(Logger.class);
    }
  }

  @Configuration
  @AutoConfigureBefore(BeanConditional.class)
  static class MockMarkerAutoConfiguration {

    @Bean
    public BeanConditional.Marker mockMarker() {
      return mock(BeanConditional.Marker.class);
    }
  }
}
