package org.example.demo.aop.spring.pfb;

import org.example.demo.aop.pointcut.nmath.Guitar;

public class SingerIImpl implements SingerI {
    @Override
    public void sing() {
        System.out.println("sing");
    }

    @Override
    public void sing(Guitar guitar) {
        System.out.println("sing " + guitar.play());
    }

    @Override
    public void talk() {
        System.out.println("talk");
    }
}
