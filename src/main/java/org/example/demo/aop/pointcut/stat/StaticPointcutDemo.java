package org.example.demo.aop.pointcut.stat;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class StaticPointcutDemo {
    public static void main(String[] args) {
        Singer mySinger = new MySinger();
        Singer yourSinger = new YourSinger();

        SimpleStaticPointcut pointcut = new SimpleStaticPointcut();
        SimpleAdvice advice = new SimpleAdvice();
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(mySinger);
        Singer myProxy = (Singer) factory.getProxy();

        factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(yourSinger);
        Singer yourProxy = (Singer) factory.getProxy();

        myProxy.sing();
        yourProxy.sing();
    }
}
