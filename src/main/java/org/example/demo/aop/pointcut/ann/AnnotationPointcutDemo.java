package org.example.demo.aop.pointcut.ann;

import org.example.demo.aop.pointcut.nmath.Guitar;
import org.example.demo.aop.pointcut.stat.SimpleAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class AnnotationPointcutDemo {
    public static void main(String[] args) {
        AnnotatedSinger target = new AnnotatedSinger();
        AnnotationMatchingPointcut pc =
                AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvisor(new DefaultPointcutAdvisor(pc, new SimpleAdvice()));
        AnnotatedSinger proxy = (AnnotatedSinger) factory.getProxy();

        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
    }
}
