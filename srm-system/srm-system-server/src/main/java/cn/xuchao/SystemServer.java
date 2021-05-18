package cn.xuchao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xuchao
 */
@SpringBootApplication
@EnableEurekaClient
public class SystemServer {

    public static void main(String[] args) {
        SpringApplication.run(SystemServer.class, args);
    }

}
