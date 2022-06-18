package org.example.demo.aop.spring.pfb;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Documentarist {
    protected final SingerI singer;
    public void execute(){
        singer.sing();
        singer.talk();
    }
}
