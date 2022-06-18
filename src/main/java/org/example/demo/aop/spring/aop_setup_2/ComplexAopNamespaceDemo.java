package org.example.demo.aop.spring.aop_setup_2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ComplexAopNamespaceDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:setups/app-context-xml-02.xml");
        ctx.refresh();

        NewDocumentarist bean = ctx.getBean(NewDocumentarist.class);
        bean.execute();
        ctx.close();
    }
}
