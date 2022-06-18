package org.example.demo.aop.spring.ann;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.demo.aop.spring.aop_setup_2.Guitar;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotatedAdvice {
    @Pointcut("execution(* sing*(org.example.demo.aop.spring.aop_setup_2.Guitar)) && args(value)")
    public void singExecution(Guitar value){

    }

    @Pointcut("bean(john*)")
    public void isJohn(){

    }

    @Before(value = "singExecution(value) && isJohn()", argNames = "joinPoint,value")
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar value){
        if (value.getBrand().equals("Brandon")){
            Signature signature = joinPoint.getSignature();
            System.out.println("Execution: " +
                    signature.getDeclaringTypeName() + " " +
                    signature.getName());
        }
    }

    @Around(value = "singExecution(value) && isJohn()", argNames = "pjp,value")
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
