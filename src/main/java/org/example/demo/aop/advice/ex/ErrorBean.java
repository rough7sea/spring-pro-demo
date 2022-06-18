package org.example.demo.aop.advice.ex;

public class ErrorBean {
    public void errorMethod() throws Exception{
        throw new Exception("My Exception");
    }

    public void otherErrorMethod() throws IllegalArgumentException{
        throw new IllegalArgumentException("My IllegalArgumentException");
    }
}
