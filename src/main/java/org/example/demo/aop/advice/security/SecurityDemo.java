package org.example.demo.aop.advice.security;

import org.springframework.aop.framework.ProxyFactory;

public class SecurityDemo {
    public static void main(String[] args) {
        SecurityManager sm = new SecurityManager();

        SecureBean bean = getSecureBean();

        sm.login("Me", "password");
        bean.secureTest();
        sm.logout();

        try {
            sm.login("Not me", "some");
            bean.secureTest();
        } catch (SecurityException e) {
            e.printStackTrace();
        } finally {
            sm.logout();
        }

        try {
            bean.secureTest();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private static SecureBean getSecureBean(){
        SecureBean target = new SecureBean();

        SecureAdvice advice = new SecureAdvice();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvice(advice);

        return (SecureBean) factory.getProxy();
    }
}
