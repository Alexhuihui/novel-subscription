package top.alexmmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author 汪永晖
 */
@EnableJpaAuditing
@EnableEurekaClient
@SpringBootApplication
public class SubscriptionFictionApplication {

    public static void main(String[] args) {

        SpringApplication.run(SubscriptionFictionApplication.class, args);
    }
}
