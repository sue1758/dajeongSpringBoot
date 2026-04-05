package com.example.firstproject;

import com.example.firstproject.dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 사용자 요청을 처리하는 컨트롤러 클래스
public class ArticleController {

    @GetMapping("/articles/new") // GET 요청: 브라우저에서 이 주소로 접속하면
    public String newArticleForm() {
        return "articles/new"; // 이 위치의 HTML(뷰 템플릿)을 화면에 띄움
    }

    @PostMapping("/articles/create") // POST 요청: 폼에서 데이터 전송 시 실행됨
    public String createArticle(ArticleForm form) { // 폼에서 넘어온 데이터가 DTO에 자동 바인딩됨

        System.out.println(form.toString()); // 넘어온 데이터 확인용 콘솔 출력

        return ""; // 처리 완료 후 이동할 페이지
    }
}