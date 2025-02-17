package boxx_ham.Blog_Project.service;

import org.springframework.stereotype.Service;

import boxx_ham.Blog_Project.domain.Article;
import boxx_ham.Blog_Project.dto.AddArticleRequest;
import boxx_ham.Blog_Project.repository.BlogRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor    // final이 붙거나 @NotNul이 붙은 필드의 생성자 추가
@Service    // 해당 클래스를 빈으로 서블릿 컨테이너에 등록
public class BlogService {
    private final BlogRepository blogRepository;
    
    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {    // save()로 AddArticleRequest 클래스에 저장된 값들을 article 데이터베이스에 저장
        return blogRepository.save(request.toEntity());
    }
}
