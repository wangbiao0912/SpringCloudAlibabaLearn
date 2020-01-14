package com.after00.config;



import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@Order(value = 3)
public class StartRunnerComponent implements CommandLineRunner {



    @Override
    public void run(String... args) throws Exception {

        log.info("程序启动---执行方法");
    }
}
