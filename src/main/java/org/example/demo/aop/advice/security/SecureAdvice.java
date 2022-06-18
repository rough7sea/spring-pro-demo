package org.example.demo.aop.advice.security;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecureAdvice implements MethodBeforeAdvice {
    private final SecurityManager securityManager;

    public SecureAdvice() {
        this.securityManager = new SecurityManager();
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        UserInfo user = securityManager.getLogOutUser();
        if (user == null){
            System.out.println("No user authenticated");
            throw new SecurityException("No user authenticated");
        } else if ("Me".equals(user.getUserName())){
            System.out.println("Me logged in");
        } else {
            System.out.format("User %s not allowed\n", user.getUserName());
            throw new SecurityException(String.format("User %s not allowed", user.getUserName()));
        }
    }
}
