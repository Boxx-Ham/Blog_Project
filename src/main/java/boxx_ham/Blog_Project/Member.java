package boxx_ham.Blog_Project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * @Entity
 * Member 객체를 JPA가 관리하는 엔티티로 지정 -> Member 클래스와 데이터베이스의 테이블 매핑
 * @Entity 속성 중에 name 사용하면 name의 값을 가진 테이블 이름과 매핑, 테이블 이름 지정하지 않으면 클래스 이름과 같은 이름의 테이블과 매핑
 * e.g., @Entity -> member, @Entity(name = "member_list") -> member_list
 * 
 * @NoArgsConstructor
 * 매개변수 없는 기본 생성자 생성
 * aceess 속성 사용 안하면 public 으로 기본 생성자 생성하지만 access 속성을 통해 접근 제한자 변경 가능
 * AccessLevel.PROTECTED -> public이 아니라 protected 로 접근 제한자 변경
 * 
 * @Id 
 * Long 타입의 id 필드를 테이블의 기본키로 지정
 * 
 * @GeneratedValue
 * 기본키의 생성 방식
 * 자동키 생성 설정 방식
 * 1. AUTO : 선택한 데이터베이스 방언(dialect)에 따라 방식을 자동으로 선택 (기본값)
 * 2. IDENTITY : 기본키 생성을 데이터베이스에 위임(= AUTO_INCREMENT)
 * 3. SEQUENCE : 데이터베이스 시퀀스를 사용해서 기본키 할당하는 방법 (오라클에서 주로 사용)
 * 4. TABLE : 키 생성 테이블 사용
 * 
 * @Column
 * 데이터베이스의 칼럼과 필드 매핑
 * 대표적인 @Column 애너테이션 속성
 * 1. name : 필드와 매핑할 칼럼 이름 (설정하지 않으면 필드 이름으로 지정)
 * 2. nullable : 칼럼의 null 허용 여부 (NOT NULL 여부 / 설정하지 않으면 true(nullable))
 * 3. unique : 칼럼의 유일한 값(unique) 여부 (설정하지 않으면 false(non.unique))
 * 4. columnDefinition : 칼럼 정보 설정 (default 값 줄 수 있음)
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자 
@AllArgsConstructor
@Getter
@Entity // 엔티티로 지정
public class Member {
    @Id // id 필드를 기본키로 지정정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;    // DB 테이블의 'id' 칼럼과 매칭

    @Column(name = "name", nullable = false)    // name이라는 not null 칼럼과 매핑
    private String name;    // DB 테이블의 'name' 칼럼과 매칭

    // 수정 메서드
    public void changeName(String name) {
        this.name = name;
    }
}
