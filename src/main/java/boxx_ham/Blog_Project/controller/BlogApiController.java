package boxx_ham.Blog_Project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import boxx_ham.Blog_Project.domain.Article;
import boxx_ham.Blog_Project.dto.AddArticleRequest;
import boxx_ham.Blog_Project.service.BlogService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController // HTTP Responce Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {
    private final BlogService blogService;

    // HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")   // URL = '/api/articles'
    /*
     * @RequestBody
     * HTTP 요청할 때 응답에 해당하는 값을 @RequestBody 애너테이션이 붙은 대상 객체에 매핑 (AddArticleRequest)
     */
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);    // 응답 코드로 201, 즉, Created 응답하고 테이블에 저장된 객체 반환
    }
}
