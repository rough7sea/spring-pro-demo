package org.example.demo.aop.plugin;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public aspect MessageWrapper{
    private String prefix;
    private String suffix;

    pointcut doWriting():
            execution(* org.example.demo.aop.plugin.MessageWritter.writeMessage());

    before() : doWriting(){
        System.out.println(prefix);
    }

    after() : doWriting(){
        System.out.println(suffix);
    }

}