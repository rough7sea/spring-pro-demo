package org.example.demo.aop.advice.ex;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

public class SimpleThrowsAdvice implements ThrowsAdvice {
    public static void main(String[] args) {
        ErrorBean errorBean = new ErrorBean();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(errorBean);
        factory.addAdvice(new SimpleThrowsAdvice());
        ErrorBean proxy = (ErrorBean) factory.getProxy();

        try {
            proxy.errorMethod();
        } catch (Exception ignored) {}

        try {
            proxy.otherErrorMethod();
        } catch (Exception ignored) {}
    }

    public void afterThrowing(Exception ex) throws Throwable{
        System.out.println("********");
        System.out.println("Exception capture = " + ex.getClass().getName());
        System.out.println("********");
    }

    public void afterThrowing(Method method, Object[] args,
                              Object target, IllegalArgumentException ex) throws Throwable{
        System.out.println("********");
        System.out.println("IllegalArgumentException capture = " + ex.getClass().getName());
        System.out.println("Method = " + method.getName());
        System.out.println("********");
    }
}
