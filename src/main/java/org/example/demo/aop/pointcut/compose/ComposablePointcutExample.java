package org.example.demo.aop.pointcut.compose;

import org.example.demo.aop.pointcut.flow.SimpleBeforeAdvice;
import org.example.demo.aop.pointcut.nmath.Guitar;
import org.example.demo.aop.pointcut.nmath.OneMoreSinger;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

public class ComposablePointcutExample {
    public static void main(String[] args) {
        OneMoreSinger singer = new OneMoreSinger();

        ComposablePointcut pointcut = new ComposablePointcut(

//                cls -> cls.equals(OneMoreSinger.class),
                ClassFilter.TRUE,
                new SingMethodMather());
        testInvoke(getProxy(pointcut, singer));
        pointcut.union(new TalkMethodMatcher()); // or
        testInvoke(getProxy(pointcut, singer));
        pointcut.intersection(new RestMethodMather()); // and
        testInvoke(getProxy(pointcut, singer));
    }

    private static int testCount = 1;
    private static void testInvoke(OneMoreSinger proxy) {
        System.out.println("Test " + testCount++ + " >>");
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.rest();
        proxy.talk();
        proxy.sist();
        System.out.println();
    }

    private static OneMoreSinger getProxy(ComposablePointcut pc, OneMoreSinger singer) {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(singer);
        factory.addAdvisor(advisor);
        return (OneMoreSinger) factory.getProxy();
    }


    private static class SingMethodMather extends StaticMethodMatcher{
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return method.getName().startsWith("si");
        }
    }

    private static class TalkMethodMatcher extends StaticMethodMatcher{
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return "talk".equals(method.getName());
        }
    }

    private static class RestMethodMather extends StaticMethodMatcher{
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return method.getName().endsWith("st");
        }
    }
}
