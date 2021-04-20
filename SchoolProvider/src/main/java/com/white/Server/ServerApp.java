package com.white.Server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/25 11:57
 */
@SpringBootApplication
@ComponentScan("com.white")
@EnableTransactionManagement
public class ServerApp {
    public static void main(final String[] args) {
        new SpringApplicationBuilder(ServerThree.class)
                .properties("spring.config.location=classpath:/application-one.yml")
                .run(args);
    }
}
