package com.example.firstproject;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.repository.ArticleRepository;

import java.util.List;

@Slf4j
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
        log.info(form.toString());
        //1.DTO를 엔티티로 변환
        //System.out.println(form.toString()); // 넘어온 데이터 확인용 콘솔 출력
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(form.toString());

        //2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return ""; // 처리 완료 후 이동할 페이지
    }
    @GetMapping("/articles/{id}") //데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){
        log.info("id="+id); //id를 잘 받았는지 확인하는 로그 찍기
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 3. 뷰 페이지 반환하기
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 데이터 가져오기
        List<Article> articleEntityList = (List<Article>) articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //3. 뷰 페이지 설정하기
        return "articles/index";
    }
}