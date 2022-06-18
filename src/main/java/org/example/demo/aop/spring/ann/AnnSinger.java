package org.example.demo.aop.spring.ann;

import org.example.demo.aop.spring.aop_setup_2.Guitar;
import org.example.demo.aop.spring.aop_setup_2.SingerI;
import org.springframework.stereotype.Component;

@Component("johnMayer")
public class AnnSinger implements SingerI {
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
