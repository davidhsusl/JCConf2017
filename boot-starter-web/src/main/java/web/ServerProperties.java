package web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** @author David Hsu */
@Getter
@Setter
@ConfigurationProperties("server.port.random")
public class ServerProperties {

  private boolean enable = false;

  private int min = 1;

  private int max = 65535;
}
