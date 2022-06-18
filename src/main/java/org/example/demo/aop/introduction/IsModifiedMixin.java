package org.example.demo.aop.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IsModifiedMixin
        extends DelegatingIntroductionInterceptor
        implements IsModified {

    private boolean isModified = false;

    private final Map<Method, Method> methodCache = new HashMap<>();

    @Override
    public boolean isModified() {
        return this.isModified;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        if (!this.isModified){
            if (mi.getMethod().getName().startsWith("set")
                    && mi.getArguments().length == 1){
                Method getter = getGetter(mi.getMethod());
                if (getter != null){
                    Object newValue = mi.getArguments()[0];
                    Object oldValue = getter.invoke(mi.getThis(), (Object[]) null);
                    if (oldValue == null && newValue == null){
                        this.isModified = false;
                    } else if (newValue == null || oldValue == null) {
                        this.isModified = true;
                    } else {
                        this.isModified = !newValue.equals(oldValue);
                    }
                }
            }
        }
        return super.invoke(mi);
    }

    private Method getGetter(Method setter) {
        Method getter = methodCache.get(setter);
        if (getter != null){
            return getter;
        }
        String getterName = setter.getName()
                .replace("set", "get");
        try {
            getter = setter.getDeclaringClass()
                    .getMethod(getterName, (Class<?>[]) null); // can be null, there is check inside
            synchronized (methodCache){
                methodCache.put(setter, getter);
            }
            return getter;
        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
            return null;
        }
    }
}
