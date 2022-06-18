package org.example.demo.aop.pointcut.flow;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ControlFlowDemo {
    public static void main(String[] args) {
        new ControlFlowDemo().run();
    }

    private void run() {
        TestBean target = new TestBean();

        ControlFlowPointcut pc = new ControlFlowPointcut(ControlFlowDemo.class, "test");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());

        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvisor(advisor);

        TestBean proxy = (TestBean) factory.getProxy();
        System.out.println("Normal invoke");
        proxy.foo();
        System.out.println("Invoke under Control Flow");
        test(proxy);
        netest(proxy);
        test();
    }

    private void test(TestBean bean){
        bean.foo();
    }

    private void netest(TestBean bean){
        bean.foo();
    }

    private void test(){
        System.out.println("Just test()");
    }
}
