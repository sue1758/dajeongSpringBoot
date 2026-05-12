package com.example.firstproject;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.repository.ArticleRepository;

@Controller // 사용자 요청을 처리하는 컨트롤러 클래스
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new") // GET 요청: 브라우저에서 이 주소로 접속하면
    public String newArticleForm() {
        return "articles/new"; // 이 위치의 뷰 템플릿을 화면에 띄움
    }

    @PostMapping("/articles/create") // 폼에서 데이터 전송 시 실행됨
    public String createArticle(ArticleForm form) { // 폼에서 넘어온 데이터가 DTO에 자동 바인딩됨

        System.out.println(form.toString()); // 넘어온 데이터 확인용 콘솔 출력
        Article article = form.toEntity();
        System.out.println(form.toString());

        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return ""; // 처리 완료 후 이동할 페이지
    }
}