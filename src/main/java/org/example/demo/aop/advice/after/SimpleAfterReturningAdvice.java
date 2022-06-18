package org.example.demo.aop.advice.after;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.aop.advice.security.SecureBean;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

@Slf4j
public class SimpleAfterReturningAdvice implements AfterReturningAdvice {

    public static void main(String[] args) {
        SecureBean bean = new SecureBean();

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(bean);
        factory.addAdvice(new SimpleAfterReturningAdvice());

        SecureBean proxy = (SecureBean) factory.getProxy();
        proxy.secureTest();
    }
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        log.info("Advice after " + method.getName() + " with value = " + returnValue);
    }
}
