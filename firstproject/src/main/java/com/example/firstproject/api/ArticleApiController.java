package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired
    private ArticleService articleService; // 서비스 객체 주입
    private List<Article> createdList;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index(); // 서비스로 게시글 전체 조회
    }

    // GET
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id); // 서비스로 단일 게시글 조회
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto); // 서비스로 게시글 생성

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(
            @PathVariable Long id,
            @RequestBody ArticleForm dto) {

        Article updated = articleService.update(id, dto); // 서비스를 통해 게시글 수정

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {

        Article deleted = articleService.delete(id); // 서비스를 통해 게시글 삭제

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("/api/transaction-test") //여러 게시글 생성 요청 접수
    public ResponseEntity<List<Article>> transactionTest
            (@RequestBody List<ArticleForm> dtos) { //transcationTest() 메서드 정의
        List<Article> crreatedList = articleService.createArticles(dtos); //서비스 호출
        return(createdList != null) ? //생성 결과에 따라 응답처리
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
//
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id) { // URL의 id를 매개변수로 받음
//        return articleRepository.findById(id).orElse(null); // id로 데이터 조회 후 반환
//    }
//
//    // POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) { // REST API에서 데이터를 받을 때는 @RequestBody 필요
//        Article article = dto.toEntity(); // DTO를 엔티티로 변환
//        return articleRepository.save(article); // 엔티티를 DB에 저장 후 반환
//    }
//
//    // PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id,
//                                          @RequestBody ArticleForm dto) {
//        // 1. DTO -> 엔티티 변환하기
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
//        // 2. 타깃 조회하기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 잘못된 요청 처리하기
//        if (target == null || !id.equals(article.getId())) {
//            // 400, 잘못된 요청 응답!
//            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 4. 업데이트 및 정상 응답(200)하기
//        target.patch(article); // 기존 데이터에 새 데이터 붙이기
//        Article updated = articleRepository.save(target); // 수정 내용 DB에 최종 저장
//        return ResponseEntity.status(HttpStatus.OK).body(updated); // 정상 응답
//    }
//
//    // DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        // 1. 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 2. 잘못된 요청 처리하기 (삭제할 대상이 없는 경우)
//        if (target == null) {
//            log.info("잘못된 요청! id: {}", id);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 3. 대상 삭제하기
//        articleRepository.delete(target);
//
//        // 4. 데이터 반환 (본문 없이 상태 코드만 200 OK 응답)
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
// }