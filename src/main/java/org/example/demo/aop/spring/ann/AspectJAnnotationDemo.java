package org.example.demo.aop.spring.ann;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;



@SpringBootApplication
@EnableAspectJAutoProxy(
//        proxyTargetClass = true
)
public class AspectJAnnotationDemo {
    public static void main(String[] args) {
        SpringApplication.run(AspectJAnnotationDemo.class);
    }

    @Autowired
    ApplicationContext context;

    @Bean
    public CommandLineRunner runner(){
        return args -> {
            NewDocumentarist documentarist = context.getBean(NewDocumentarist.class);
            documentarist.execute();
        };
    }
}
