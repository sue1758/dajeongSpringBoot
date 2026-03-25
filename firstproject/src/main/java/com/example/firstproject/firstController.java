package com.example.firstproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class firstController {
    @GetMapping("/hi")
    public String niceToMeetyou(){
        return "greetings";
    }
}
