package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import web.EnableRandomPort;

@EnableRandomPort(minPort = 50000, maxPort = 59999)
@SpringBootApplication
public class RandomPortSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(RandomPortSampleApplication.class, args);
  }
}
