package org.example.demo.aop.pointcut.ann;

import org.example.demo.aop.pointcut.nmath.Guitar;
import org.example.demo.aop.pointcut.stat.Singer;

public class AnnotatedSinger implements Singer {
    @Override
    public void sing() {
        System.out.println("Song 1");
    }

    @AdviceRequired
    public void sing(Guitar guitar) {
        System.out.println("Song via " + guitar.play());
    }

    public void rest() {
        System.out.println("rest");
    }
}
