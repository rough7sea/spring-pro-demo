package org.example.demo.scheduling;

import lombok.Getter;
import org.example.demo.scheduling.events.CustomSpringEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Config {

    @Autowired
    CustomSpringEventPublisher customSpringEventPublisher;
    public void setCron(String cron) {
        this.cron = cron;
        customSpringEventPublisher.publishCustomEvent(this);
    }

    @Getter
    private String cron = "*/1 * * * * *";


}
