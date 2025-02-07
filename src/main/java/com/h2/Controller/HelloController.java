package com.h2.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h2.Security.UserPrincipal;

@RestController
@RequiredArgsConstructor
public class HelloController {

    @GetMapping("/")
    public String Greeting() {
        return "Hello";
    }

    @GetMapping("/secured")
    public String Secured(@AuthenticationPrincipal UserPrincipal principal) {
        return "If you see this, you are authenticated as " + principal.getEmail() 
                + " UserId " + principal.getUserId();
    }
}
