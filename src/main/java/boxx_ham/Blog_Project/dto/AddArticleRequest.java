package boxx_ham.Blog_Project.dto;

import boxx_ham.Blog_Project.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor  // 기본 생성자 추가 애너테이션
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가 애너테이션
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    // 빌더 패턴을 사용해 DTO(Data Transfer Object)를 엔터티로 만들어주는 메서드
    public Article toEntity() {
        // 생성자를 사용해 객체 생성
        return Article.builder().title(title).content(content).build();
    }
}
