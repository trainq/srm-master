package cn.xuchao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xkhy
 */
@SpringBootApplication
@EnableEurekaClient
public class MaterialServer {

    public static void main(String[] args) {
        SpringApplication.run(MaterialServer.class, args);
    }

}
