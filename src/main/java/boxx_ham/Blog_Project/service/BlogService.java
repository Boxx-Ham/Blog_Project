package boxx_ham.Blog_Project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import boxx_ham.Blog_Project.domain.Article;
import boxx_ham.Blog_Project.dto.AddArticleRequest;
import boxx_ham.Blog_Project.dto.UpdateArticleRequest;
import boxx_ham.Blog_Project.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor    // final이 붙거나 @NotNul이 붙은 필드의 생성자 추가
@Service    // 해당 클래스를 빈으로 서블릿 컨테이너에 등록
public class BlogService {
    private final BlogRepository blogRepository;
    
    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {    // save()로 AddArticleRequest 클래스에 저장된 값들을 article 데이터베이스에 저장
        return blogRepository.save(request.toEntity());
    }

    // 모든 블로그 글 조회 메서드
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 하나의 블로그 글 조회 메서드
    public Article findById(long id) {
        // id가 없으면 IllegalArgumentException 예외 발생
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    // 하나의 블로그 글 삭제 메서드
    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    // 하나의 블로그 글 수정 메서드
    @Transactional  // 트랜잭션 메서드 : 매칭한 메서드를 하나의 트랜잭션으로 묶는 역할
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
