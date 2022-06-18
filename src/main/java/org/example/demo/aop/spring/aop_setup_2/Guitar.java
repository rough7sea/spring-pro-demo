package org.example.demo.aop.spring.aop_setup_2;

import lombok.Getter;
import lombok.Setter;

public class Guitar {
    @Getter
    @Setter
    private String brand = "Martin";
    public String play(){
        return "A D C E Em";
    }
}
