package web;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/** @author David Hsu */
@Import(RandomPortSelector.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableRandomPort {

  int minPort() default 1;

  int maxPort() default 65535;
}
