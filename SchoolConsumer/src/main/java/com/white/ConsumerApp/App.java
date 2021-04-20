/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.white.ConsumerApp;

import com.whit.Socket.Annotation.EnableSocketClientScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/25 19:22
 */
@SpringBootApplication
@ComponentScan("com.white")
@EnableSocketClientScan(baseUrl = "com.white.Service")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
