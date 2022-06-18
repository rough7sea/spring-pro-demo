package org.example.demo.aop.pointcut.dynamic;

public class SampleBean {
    public void foo(int x){
        System.out.println("foo() with " + x);
    }
    public void bar(){
        System.out.println("bar()");
    }
}
