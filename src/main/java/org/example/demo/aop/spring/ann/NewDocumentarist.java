package org.example.demo.aop.spring.ann;

import lombok.AllArgsConstructor;
import org.example.demo.aop.spring.aop_setup_2.Guitar;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NewDocumentarist {
    protected final AnnSinger singer;

    public void execute(){
        singer.sing();
        Guitar guitar = new Guitar();
        guitar.setBrand("Brandon");
        singer.sing(guitar);
        singer.sing(new Guitar());
        singer.talk();
    }

}
