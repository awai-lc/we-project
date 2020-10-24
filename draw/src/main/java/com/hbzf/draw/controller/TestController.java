package com.hbzf.draw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("draw")
public class TestController {

    @RequestMapping("/test")
    public String health(){
        return "";
    }
}
