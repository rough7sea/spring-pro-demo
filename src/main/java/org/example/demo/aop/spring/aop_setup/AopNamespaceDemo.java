package org.example.demo.aop.spring.aop_setup;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AopNamespaceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:setups/app-context-xml-01.xml");
        ctx.refresh();

        NewDocumentarist bean = ctx.getBean(NewDocumentarist.class);
        bean.execute();
        ctx.close();
    }
}
