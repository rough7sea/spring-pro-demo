package org.example.demo.aop.pointcut.dynamic;

import org.example.demo.aop.pointcut.stat.SimpleAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class DynamicPointcutDemo {
    public static void main(String[] args) {
        SampleBean target = new SampleBean();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvisor(new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAdvice()));
        SampleBean proxy = (SampleBean) factory.getProxy();

        proxy.foo(1);
        proxy.foo(10);
        proxy.foo(10);

        proxy.bar();
        proxy.bar();
        proxy.bar();
    }
}
