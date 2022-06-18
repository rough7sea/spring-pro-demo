package org.example.demo.aop.pointcut.regex;

import org.example.demo.aop.pointcut.stat.Singer;

public class RegSinger implements Singer {
    @Override
    public void sing() {
        System.out.println("Song 1");
    }
    public void sing2() {
        System.out.println("Song 2");
    }

    public void rest() {
        System.out.println("rest");
    }
}
