package org.example.demo.aop.spring.aop_setup;

import org.example.demo.aop.pointcut.nmath.Guitar;
import org.example.demo.aop.spring.pfb.Documentarist;
import org.example.demo.aop.spring.pfb.SingerI;

public class NewDocumentarist extends Documentarist {
    public NewDocumentarist(SingerI singer) {
        super(singer);
    }

    @Override
    public void execute() {
        this.singer.sing();
        this.singer.sing(new Guitar());
        this.singer.talk();
    }
}
