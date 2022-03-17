package com.redsky.myretail.controller;

import com.redsky.myretail.domain.ServiceHealth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.atomic.AtomicLong;

@Controller
public class ServiceHealthController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/service-health")
    @ResponseBody
    public ServiceHealth getServiceHealth(@RequestParam(name = "name", required = false, defaultValue = "User") String name) {
        return new ServiceHealth(counter.incrementAndGet(), String.format(template, name));
    }
}
