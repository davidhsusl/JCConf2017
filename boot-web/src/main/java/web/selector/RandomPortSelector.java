package web.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import web.EnableRandomPort;

/** @author David Hsu */
public class RandomPortSelector implements ImportSelector {

  private static final String MIN_PORT = "minPort";
  private static final String MAX_PORT = "maxPort";

  private static int minPort = 0;
  private static int maxPort = 0;

  @Override
  public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    AnnotationAttributes attributes =
        AnnotationAttributes.fromMap(
            importingClassMetadata.getAnnotationAttributes(
                EnableRandomPort.class.getName(), false));

    minPort = attributes.getNumber(MIN_PORT);
    maxPort = attributes.getNumber(MAX_PORT);

    return new String[] {EmbeddedServletContainerConfiguration.class.getName()};
  }

  public static int getMinPort() {
    return minPort;
  }

  public static int getMaxPort() {
    return maxPort;
  }
}
