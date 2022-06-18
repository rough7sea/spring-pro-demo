package org.example.demo.aop.spring.aop_setup_2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class ComplexAdvice {
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar value){
        if (value.getBrand().equals("Brandon")){
            Signature signature = joinPoint.getSignature();
            System.out.println("Execution: " +
                    signature.getDeclaringTypeName() + " " +
                    signature.getName());
        }
    }
    public Object simpleAroundAdvice(ProceedingJoinPoint pjp, Guitar value) throws Throwable {
        Signature signature = pjp.getSignature();
        System.out.println("Before execution: " +
                signature.getDeclaringTypeName() + " " +
                signature.getName());
        Object proceed = pjp.proceed();
        System.out.println("After execution: " +
                signature.getDeclaringTypeName() + " " +
                signature.getName() + "\n");
        return proceed;
    }
}
