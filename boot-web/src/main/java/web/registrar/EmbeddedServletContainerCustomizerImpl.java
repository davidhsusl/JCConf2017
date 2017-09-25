package web.registrar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.util.SocketUtils;

import java.util.HashMap;
import java.util.Map;

/** @author David Hsu */
public class EmbeddedServletContainerCustomizerImpl implements EmbeddedServletContainerCustomizer {

  private int minPort;
  private int maxPort;

  public EmbeddedServletContainerCustomizerImpl(int minPort, int maxPort) {
    this.minPort = minPort;
    this.maxPort = maxPort;
  }

  @Autowired private ConfigurableEnvironment environment;

  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);

    MutablePropertySources propertySources = environment.getPropertySources();
    Map<String, Object> serverMap = new HashMap<>();
    serverMap.put("server.port", port);
    propertySources.addFirst(new MapPropertySource("CUSTOMIZE_SERVER_PROPS", serverMap));

    container.setPort(port);
  }
}
