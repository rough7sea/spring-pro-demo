package org.example.demo.aop.advice.mi;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingDemo {
    public static void main(String[] args) {
        WorkerBean bean = getWorkerBean();
        bean.doWork(10000);
    }

    private static WorkerBean getWorkerBean() {
        ProxyFactory factory = new ProxyFactory();
        WorkerBean target = new WorkerBean();
        factory.setTarget(target);
        factory.addAdvice(new ProfilingInterceptor());
        return (WorkerBean) factory.getProxy();
    }
}
