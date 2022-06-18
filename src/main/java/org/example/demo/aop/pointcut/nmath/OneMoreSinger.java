package org.example.demo.aop.pointcut.nmath;

import org.example.demo.aop.pointcut.stat.Singer;

public class OneMoreSinger implements Singer {
    @Override
    public void sing() {
        System.out.println("Sing some");
    }

    public void sing(Guitar guitar){
        System.out.println("Guitar solo: " + guitar.play());
    }

    public void rest() {
        System.out.println("rest");
    }

    public void talk() {
        System.out.println("talk");
    }

    public void sist() {
        System.out.println("sist");
    }
}
