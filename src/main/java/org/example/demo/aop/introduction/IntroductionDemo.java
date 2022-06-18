package org.example.demo.aop.introduction;

import org.springframework.aop.framework.ProxyFactory;

public class IntroductionDemo {
    public static void main(String[] args) {
        Contact target = new Contact();
        target.setName("My name");

        IsModifiedAdvisor advisor = new IsModifiedAdvisor();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvisor(advisor);
//        factory.addAdvice(new SimpleAdvice()); // it works)
        factory.setOptimize(true); // didn't work without optimize flag

        Contact proxy = (Contact) factory.getProxy();
        IsModified proxyInterface = (IsModified) proxy;

        System.out.println("Is Contact? " + (proxy instanceof Contact));
        System.out.println("Is IsModified? " + (proxy instanceof IsModified));
        System.out.println("Has been modified? " + proxyInterface.isModified());

        proxy.setName("My name");
        System.out.println("Has been modified? " + proxyInterface.isModified());
        proxy.setName("Not my name");
        System.out.println("Has been modified? " + proxyInterface.isModified());
        System.out.println(proxy);
    }
}
