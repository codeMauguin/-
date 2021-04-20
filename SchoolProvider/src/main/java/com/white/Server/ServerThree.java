package com.white.Server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/30 9:17
 */
@SpringBootApplication
@ComponentScan("com.white")
@EnableTransactionManagement
@MapperScan("com.white.dao")
public class ServerThree {
    public static void main(final String[] args) {
        new SpringApplicationBuilder(ServerThree.class)
                .properties("spring.config.location=classpath:/application-three.yml")
                .run(args);
    }
}
