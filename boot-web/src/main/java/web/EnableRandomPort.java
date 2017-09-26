package web;

import org.springframework.context.annotation.Import;
import org.springframework.util.SocketUtils;
import web.selector.RandomPortSelector;

import java.lang.annotation.*;

/** @author David Hsu */
@Import(RandomPortSelector.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableRandomPort {

  int minPort() default SocketUtils.PORT_RANGE_MIN;

  int maxPort() default SocketUtils.PORT_RANGE_MAX;
}
