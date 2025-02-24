package boxx_ham.Blog_Project.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)  // 엔터티의 생성 및 수정 시간을 자동으로 감시하고 기록하기 위해 사용
@Entity // 엔터티로 지정
@Getter // getId(), getTitle() 같이 필드의 값을 가져오는 애너테이션
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자를 protected로 생성성
public class Article {
    // 1. ID            BIGINT          NOT NULL    PK      일련번호, 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    // 2. title         VARCHAR(255)    NOT NULL            게시물의 제목
    @Column(name = "title", nullable = false)   // 'title'이라는 not null 칼럼과 매핑
    private String title;

    // 3. content       VARCHAR(255)    NOT NULL            내용           
    @Column(name = "content", nullable = false) // 'content'라는 not null 칼럼과 매핑
    private String content;

    // 4. created_at    DATE            NULL             생성시간            
    @CreatedDate    // 엔터티가 생성될 때 생성 시간 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 5. updated_at    DATE            NULL            수정시간
    @LastModifiedDate   // 엔터티가 수정될 때 수정 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /*
     * @Builder
     * lombok에서 지원하는 애너테이션
     * 빌더 패턴으로 객체 생성 가능
     * 빌더 패턴 사용하면 객체를 유연하고 직관적으로 생성 가능
     * 즉, 빌더 패턴을 사용하면 어느 필드에 어떤 값이 들어가는지 명시적으로 파악 가능
     * 
     * e.g., 빌더 패턴 사용 X
     * new Article("abc", "def");
     * 
     * e.g., 빌더 패턴 사용 시
     * Article.builder().title("abc").content("def").build();
     */
    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 블로그 수정 메서드 (엔티티에 요청받은 내용으로 값 수정)
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
