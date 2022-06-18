package org.example.demo.aop.spring.aop_setup_2;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewDocumentarist{
    protected final SingerI singer;

    public void execute() {
        singer.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Brandon");
        singer.sing(guitar);
        singer.sing(new Guitar());
        singer.talk();
    }
}
