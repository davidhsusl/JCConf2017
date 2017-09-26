package web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.SocketUtils;

/** @author David Hsu */
@Getter
@Setter
@ConfigurationProperties("server.port.random")
public class ServerProperties {

  private boolean enable = false;

  private int min = SocketUtils.PORT_RANGE_MIN;

  private int max = SocketUtils.PORT_RANGE_MAX;
}
