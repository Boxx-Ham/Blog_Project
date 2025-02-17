package boxx_ham.Blog_Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import boxx_ham.Blog_Project.domain.Article;

// JpaRepository 클래스 상속받을 때 엔터티 Article과 엔터티의 PK 타입 Long을 인수로 넣어 JpaRepository에서 제공하는 여러 메서드 사용 가능
public interface BlogRepository extends JpaRepository<Article, Long> {
}