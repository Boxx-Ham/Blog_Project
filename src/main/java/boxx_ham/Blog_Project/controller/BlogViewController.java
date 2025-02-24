package boxx_ham.Blog_Project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import boxx_ham.Blog_Project.domain.Article;
import boxx_ham.Blog_Project.dto.ArticleListViewResponse;
import boxx_ham.Blog_Project.dto.ArticleViewResponse;
import boxx_ham.Blog_Project.service.BlogService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream().map(ArticleListViewResponse::new).toList();

        model.addAttribute("articles", articles);   // 블로그 글 리스트 저장

        return "articleList";   // articleList.html 뷰 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";   // article.html 뷰 조회
    }

    // 새 글 생성 및 수정
    @GetMapping("/new-article")
    // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑(id는 없을 수도 있음)
    // id를 인식하지 못해 명시적으로 선언
    public String newArticle(@RequestParam(name="id", required=false) Long id, Model model) {
        // id가 없으면 새 글 생성
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());

        // id가 있으면 글 수정
        } else {
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";   // newArticle.html 뷰 조회
    }
}
