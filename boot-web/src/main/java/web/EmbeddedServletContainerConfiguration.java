package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.util.SocketUtils;

import java.util.HashMap;
import java.util.Map;

/** @author David Hsu */
public class EmbeddedServletContainerConfiguration {

  @Autowired private ConfigurableEnvironment environment;

  @Bean
  public EmbeddedServletContainerCustomizer containerCustomizer() {
    return container -> {
      int port =
          SocketUtils.findAvailableTcpPort(
              RandomPortSelector.getMinPort(), RandomPortSelector.getMaxPort());

      MutablePropertySources propertySources = environment.getPropertySources();
      Map<String, Object> serverMap = new HashMap<>();
      serverMap.put("server.port", port);
      propertySources.addFirst(new MapPropertySource("CUSTOMIZE_SERVER_PROPS", serverMap));

      container.setPort(port);
    };
  }
}
