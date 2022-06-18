package org.example.demo.aop.plugin;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectJDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:setups/app-context-xml-plugin.xml");
        ctx.refresh();
        MessageWriter writer = new MessageWriter();
        writer.writeMessage();
        writer.foo();
    }
}
