package com.example.demo2;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.atomic.AtomicInteger;

@Named
@Singleton
public class HelloService {

    private final AtomicInteger cnt = new AtomicInteger(0);

    public String getHello() {
        return "Hello visitor #" + cnt.incrementAndGet();
    }

}
