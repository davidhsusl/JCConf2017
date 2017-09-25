package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import web.registrar.EmbeddedServletContainerCustomizerImpl;

/** @author David Hsu */
@EnableConfigurationProperties(ServerProperties.class)
@Configuration
public class WebAutoConfiguration {

  @Autowired private ServerProperties properties;

  @ConditionalOnProperty(name = "server.port.random.enable", havingValue = "true")
  @Bean
  public EmbeddedServletContainerCustomizer containerCustomizer() {
    return new EmbeddedServletContainerCustomizerImpl(properties.getMin(), properties.getMax());
  }
}
