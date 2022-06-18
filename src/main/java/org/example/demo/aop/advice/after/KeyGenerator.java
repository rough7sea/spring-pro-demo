package org.example.demo.aop.advice.after;

import java.util.Random;

public class KeyGenerator {
    protected static final long WEAK_KEY = 0xFFFFFFF0000000L;
    protected static final long STRONG_KEY = 0xACDF03F590AE56L;
    private final Random rand = new Random();
    public long getKey(){
        int nextInt = rand.nextInt(3);
        if (nextInt == 1){
            return WEAK_KEY;
        }
        return STRONG_KEY;
    }
}
