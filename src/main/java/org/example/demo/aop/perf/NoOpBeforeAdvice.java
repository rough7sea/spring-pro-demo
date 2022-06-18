package org.example.demo.aop.perf;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class NoOpBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        int a = 12;
        int b = 10;
        int c = a - b;
        // no action
    }
}
