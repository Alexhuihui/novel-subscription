package top.alexmmd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 汪永晖
 */
@EnableJpaAuditing
@EnableEurekaClient
@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
public class SubscriptionFictionApplication {

    public static void main(String[] args) {

        SpringApplication.run(SubscriptionFictionApplication.class, args);
    }

}
