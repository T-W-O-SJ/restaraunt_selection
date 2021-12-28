package com.git.selection.web;

import org.springframework.web.bind.annotation.GetMapping;

public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:swagger-ui.html";
    }
}