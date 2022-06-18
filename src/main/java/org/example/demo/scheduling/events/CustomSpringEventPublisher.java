package org.example.demo.scheduling.events;

import lombok.extern.slf4j.Slf4j;
import org.example.demo.scheduling.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomSpringEventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final Config config) {
        log.info("Publishing custom event. ");
        GenericSpringEvent<Config> event = new GenericSpringEvent<>(config, true);
        applicationEventPublisher.publishEvent(event);
    }
}
