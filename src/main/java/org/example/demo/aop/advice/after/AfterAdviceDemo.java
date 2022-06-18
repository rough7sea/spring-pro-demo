package org.example.demo.aop.advice.after;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceDemo {

    private static KeyGenerator keyGenerator(){
        KeyGenerator target = new KeyGenerator();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(new WeakKeyCheckAdvice());
        return (KeyGenerator) factory.getProxy();
    }
    public static void main(String[] args) {
        KeyGenerator generator = keyGenerator();
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Key = " + generator.getKey());
            } catch (SecurityException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
