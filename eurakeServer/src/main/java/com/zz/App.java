package com.zz;/**
 * @Description: 描述
 * @Author: Bsea
 * @CreateDate: 2019/11/1
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description: java类作用描述
 * @Author: Bsea
 * @CreateDate: 2019/11/1$ 20:22$
 */
@EnableEurekaServer
@SpringBootApplication
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class);
    }
}
