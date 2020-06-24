package top.alexmmd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 汪永晖
 */
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
@MapperScan("top.alexmmd.dao")
public class SubscriptionUserApplication {

    public static void main(String[] args) {

        SpringApplication.run(SubscriptionUserApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { //这里注入了就可以了
        return new BCryptPasswordEncoder();
    }
}
