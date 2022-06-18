package org.example.demo.aop.spring.aop_setup;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class SimpleAdvice {
    public void simpleBeforeAdvice(JoinPoint joinpoint){
        Signature signature = joinpoint.getSignature();
        System.out.println("Execution: " +
                signature.getDeclaringTypeName() + " " +
                signature.getName());
    }
}
