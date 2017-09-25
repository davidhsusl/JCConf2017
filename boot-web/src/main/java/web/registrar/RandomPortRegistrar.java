package web.registrar;

import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import web.EnableRandomPort;

/** @author David Hsu */
public class RandomPortRegistrar implements ImportBeanDefinitionRegistrar {

  private static final String MIN_PORT = "minPort";
  private static final String MAX_PORT = "maxPort";

  @Override
  public void registerBeanDefinitions(
      AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    AnnotationAttributes attributes =
        AnnotationAttributes.fromMap(
            importingClassMetadata.getAnnotationAttributes(
                EnableRandomPort.class.getName(), false));

    int minPort = attributes.getNumber(MIN_PORT);
    int maxPort = attributes.getNumber(MAX_PORT);

    GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
    beanDefinition.setBeanClass(EmbeddedServletContainerCustomizerImpl.class);

    ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
    constructorArgumentValues.addGenericArgumentValue(minPort);
    constructorArgumentValues.addGenericArgumentValue(maxPort);
    beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

    registry.registerBeanDefinition("containerCustomizer", beanDefinition);
  }
}
