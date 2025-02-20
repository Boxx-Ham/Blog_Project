package boxx_ham.Blog_Project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import boxx_ham.Blog_Project.domain.Article;
import boxx_ham.Blog_Project.dto.AddArticleRequest;
import boxx_ham.Blog_Project.dto.ArticleResponse;
import boxx_ham.Blog_Project.dto.UpdateArticleRequest;
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

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll().stream().map(ArticleResponse::new).toList();

        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles/{id}")
    // URL 경로에서 값 추출 (URL {id} 에 해당하는 값이 id로 들어옴)
    // @PathVariable 애너테이션 : URL에서 값을 가져오는 애너테이션
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") long id) {     // @PathVariable이 id를 제대로 인식하지 못해 명시적으로 표시
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") long id) {        // @PathVariable이 id를 제대로 인식하지 못해 명시적으로 표시
        blogService.delete(id);
        
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok().body(updatedArticle);
    }
}
