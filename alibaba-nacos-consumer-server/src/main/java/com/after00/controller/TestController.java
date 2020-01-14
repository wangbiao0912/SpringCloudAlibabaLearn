package com.after00.controller;

import com.after00.common.BaseResponse;
import com.after00.exception.TestRuntimeException;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author wangbiao
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private  RestTemplate restTemplate;
//    @Autowired
//    public TestController(RestTemplate restTemplate) {this.restTemplate = restTemplate;}

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @NacosValue(value = "${test.properties.useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;


    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://provider-service/get" + str, String.class);
    }


    @GetMapping("/get")
    public boolean get(){
        return useLocalCache;
    }
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/test")
    public BaseResponse test() {
        List<ServiceInstance> serviceInstanceList= discoveryClient.getInstances("provider-service");
        log.info(JSON.toJSONString(serviceInstanceList) +">>>>>");
        // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider-service");
        log.info(serviceInstance.getHost()+">>>>>"+serviceInstance.getUri()+">>>>>"+serviceInstance.getPort());
        String url = serviceInstance.getUri() + "/get?name=" + "didi";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        if(1==1){
            throw new TestRuntimeException("不知道什么错误"+result);
        }
        return BaseResponse.getSuccessResponse("Invoke : " + url + ", return : " + result,"正确返回");
    }
}
