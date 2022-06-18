package org.example.demo.aop.spring.pfb;

import org.example.demo.aop.pointcut.nmath.Guitar;

public interface SingerI {
    void sing();
    void sing(Guitar guitar);
    void talk();
}
