package org.example.demo.scheduling.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GenericSpringEvent<T> {
    private T target;
    protected boolean success;

}
