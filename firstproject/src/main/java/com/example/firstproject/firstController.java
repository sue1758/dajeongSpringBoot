package com.example.firstproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class firstController {
    @GetMapping("/hi") //클라이언트 요청
    public String niceToMeetyou(Model model){ //메서드 수행
        model.addAttribute("username", "hongpark"); //view의 username에 반환
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){ //컨트롤러의 메서드 변수 등록
        model.addAttribute("nickname", "홍길동");
        return "goodbye";
    }
}