package com.git.selection.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/api/")
    public String root() {
        return "redirect:swagger-ui.html";
    }
}