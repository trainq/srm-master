package cn.xuchao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author xuchao
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayServer {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServer.class ,args);
    }
}
