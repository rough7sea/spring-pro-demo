package org.example.demo.aop.perf;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ProxyPerfTest {
    public static void main(String[] args) {
        DefaultSimpleBean target = new DefaultSimpleBean();
        DefaultPointcutAdvisor pa = new DefaultPointcutAdvisor(
                new TestPointcut(), new NoOpBeforeAdvice());
        runCGLibTest(target, pa);
        runCGLibFrozenTest(target, pa);
        runOptimizeTest(target, pa);
        runJdkTest(target, pa);
    }

    private static void runCGLibTest(SimpleBean target, Advisor advisor) {
        ProxyFactory factory = new ProxyFactory();
        factory.setProxyTargetClass(true);
        factory.setTarget(target);
        factory.addAdvisor(advisor);
        SimpleBean proxy = (SimpleBean) factory.getProxy();
        System.out.format("%-30s %s\n", "Running CGLib test", test(proxy));
    }

    private static void runCGLibFrozenTest(SimpleBean target, Advisor advisor) {
        ProxyFactory factory = new ProxyFactory();
        factory.setProxyTargetClass(true);
        factory.setTarget(target);
        factory.addAdvisor(advisor);
        factory.setFrozen(true); // optimization
        SimpleBean proxy = (SimpleBean) factory.getProxy();
        System.out.format("%-30s %s\n", "Running CGLib frozen test", test(proxy));
    }

    private static void runOptimizeTest(SimpleBean target, Advisor advisor) {
        ProxyFactory factory = new ProxyFactory();
        factory.setProxyTargetClass(true);
        factory.setTarget(target);
        factory.addAdvisor(advisor);
        factory.setOptimize(true); // optimization
        SimpleBean proxy = (SimpleBean) factory.getProxy();
        System.out.format("%-30s %s\n", "Running CGLib optimize test", test(proxy));
    }

    private static void runJdkTest(SimpleBean target, Advisor advisor) {
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvisor(advisor);
        factory.setInterfaces(SimpleBean.class);

        SimpleBean proxy = (SimpleBean) factory.getProxy();
        System.out.format("%-30s %s\n", "Running JDK test", test(proxy));
    }

    private static HashMap<String, Double> test(SimpleBean proxy) {
        HashMap<String, Double> map = new LinkedHashMap<>();
        map.put("advised", methodTesting(() -> proxy.advised()));
        map.put("unadvised", methodTesting(() -> proxy.unadvised()));
        map.put("equals", methodTesting(() ->proxy.equals(proxy)));
        map.put("hashcode", methodTesting(() -> proxy.hashCode()));
        Advised advised = (Advised) proxy;
        map.put("Advised.getProxyTargetClass()", methodTesting(() -> advised.getTargetClass()));
        return map;
    }

    private static double methodTesting(Runnable runnable) {
        int circle = 100;
        long before;
        long after;
        long[] totals = new long[circle];
        for (int i = 0; i < circle; i++) {
            before = System.currentTimeMillis();
            for (int j = 0; j < 2000000; j++) {
                runnable.run();
            }
            after = System.currentTimeMillis();
            totals[i] = after - before;
        }
        return ((double) Arrays.stream(totals).reduce(0, Long::sum)) / circle;
    }
}
