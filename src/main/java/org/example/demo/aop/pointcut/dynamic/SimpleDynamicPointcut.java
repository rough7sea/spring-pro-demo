package org.example.demo.aop.pointcut.dynamic;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Arrays;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("Static check for " + method.getName());
        return "foo".equals(method.getName());
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("Check for " + method.getName() + " with args: " + Arrays.toString(args));
        int x = (int) args[0];
        return x != 10;
    }

    @Override
    public ClassFilter getClassFilter() {
        return cls -> cls.equals(SampleBean.class);
    }
}
