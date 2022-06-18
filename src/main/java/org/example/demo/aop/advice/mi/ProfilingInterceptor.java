package org.example.demo.aop.advice.mi;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ProfilingInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(invocation.getMethod().getName());
        Object proceed = invocation.proceed();

        sw.stop();
        this.dumpInfo(invocation, sw.getTotalTimeMillis());
        return proceed;
    }

    private void dumpInfo(MethodInvocation invocation, long timeMillis) {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();
        Object[] args = invocation.getArguments();
        System.out.format("M = [%s] of %s with \nArgs > [%s] \nTook = %s ms.\n",
                method.getName(), target.getClass().getName(), Arrays.toString(args), timeMillis);
    }
}
