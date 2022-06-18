package org.example.demo.aop.pointcut.regex;

import org.example.demo.aop.pointcut.stat.SimpleAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class RegexPointcutDemo {
    public static void main(String[] args) {
        RegSinger target = new RegSinger();
        JdkRegexpMethodPointcut pc = new JdkRegexpMethodPointcut();
        pc.setPattern(".*sing.*");
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvisor(new DefaultPointcutAdvisor(pc, new SimpleAdvice()));

        RegSinger proxy = (RegSinger) factory.getProxy();

        proxy.sing();
        proxy.sing2();
        proxy.rest();
    }
}
