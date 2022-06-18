package org.example.demo.aop.spring.aop_setup_2;

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
