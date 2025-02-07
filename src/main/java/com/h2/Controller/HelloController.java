package com.h2.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/")
    public String Greeting() {
        return "Hello";
    }

    @GetMapping("/secured")
    public String Secured() {
        return "If you see this, you are authenticated";
    }
}
