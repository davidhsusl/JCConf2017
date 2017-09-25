package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.util.SocketUtils;

import java.util.HashMap;
import java.util.Map;

/** @author David Hsu */
@EnableConfigurationProperties(ServerProperties.class)
@Configuration
public class WebAutoConfiguration {

  @Autowired private ServerProperties properties;

  @Autowired private ConfigurableEnvironment environment;

  @ConditionalOnProperty(name = "server.port.random.enable", havingValue = "true")
  @Bean
  public EmbeddedServletContainerCustomizer containerCustomizer() {
    return container -> {
      int port = SocketUtils.findAvailableTcpPort(properties.getMin(), properties.getMax());

      MutablePropertySources propertySources = environment.getPropertySources();
      Map<String, Object> serverMap = new HashMap<>();
      serverMap.put("server.port", port);
      propertySources.addFirst(new MapPropertySource("CUSTOMIZE_SERVER_PROPS", serverMap));

      container.setPort(port);
    };
  }
}
