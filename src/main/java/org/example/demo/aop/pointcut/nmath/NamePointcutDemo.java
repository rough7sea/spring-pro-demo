package org.example.demo.aop.pointcut.nmath;

import org.example.demo.aop.pointcut.stat.SimpleAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class NamePointcutDemo {
    public static void main(String[] args) {
        OneMoreSinger singer = new OneMoreSinger();
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("sing");
        pointcut.addMethodName("rest");

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(singer);
        factory.addAdvisor(new DefaultPointcutAdvisor(pointcut, new SimpleAdvice()));
        OneMoreSinger proxy = (OneMoreSinger) factory.getProxy();

        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
    }
}
