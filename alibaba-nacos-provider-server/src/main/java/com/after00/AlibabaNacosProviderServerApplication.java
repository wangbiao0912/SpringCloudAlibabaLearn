package com.after00;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @RestController 表示控制层
 * @EnableDiscoveryClient 开启服务注册发现功能
 */
@EnableDiscoveryClient
@SpringBootApplication
class AlibabaNacosProviderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosProviderServerApplication.class, args);
    }

    @Slf4j
    @RestController
    static class TestController {

        @GetMapping("/echo/{name}")
        public String echo(@PathVariable String name) {
            return "hello " + name;
        }
        @Autowired
        LoadBalancerClient loadBalancerClient;

        @GetMapping("/test")
        public String test() {
            // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
            ServiceInstance serviceInstance = loadBalancerClient.choose("alibaba-nacos-discovery-server");
            String url = serviceInstance.getUri() + "/hello?name=" + "didi";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);
            return "Invoke : " + url + ", return : " + result;
        }
    }
}
