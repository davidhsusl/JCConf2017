package web;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

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

    minPort = Integer.parseInt(attributes.get(MIN_PORT).toString());
    maxPort = Integer.parseInt(attributes.get(MAX_PORT).toString());

    return new String[] {EmbeddedServletContainerConfiguration.class.getName()};
  }

  public static int getMinPort() {
    return minPort;
  }

  public static int getMaxPort() {
    return maxPort;
  }
}
