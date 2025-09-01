package com.q_care.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor

public class basicController {
    @GetMapping("/about")
    public String about() {
        return "about.html";
    }
}
