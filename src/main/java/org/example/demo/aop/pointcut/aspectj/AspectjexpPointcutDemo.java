package org.example.demo.aop.pointcut.aspectj;

import org.example.demo.aop.pointcut.regex.RegSinger;
import org.example.demo.aop.pointcut.stat.SimpleAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AspectjexpPointcutDemo {
    public static void main(String[] args) {
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* sing*(..))");
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(new RegSinger());
        factory.addAdvisor(new DefaultPointcutAdvisor(pc, new SimpleAdvice()));
        RegSinger proxy = (RegSinger) factory.getProxy();

        proxy.rest();
        proxy.sing();
        proxy.sing2();
    }
}
