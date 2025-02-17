package boxx_ham.Blog_Project;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberRepositoryTests {
    @Autowired
    MemberRepository memberRepository;

    // 각 테스트 후 데이터 초기화
    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }
    
    @Sql("/insert-members.sql")
    @Test
    void getAllMembers() {
        // when
        List<Member> members = memberRepository.findAll();  // SELECT * FROM member;

        // then 
        assertThat(members.size()).isEqualTo(6);
    }

    @Sql("/insert-members.sql")
    @Test
    void getMemberById() {
        // when 
        Member member = memberRepository.findById(5L).get();    // SELECT * FROM member WHERE id = 5;

        // then
        assertThat(member.getName()).isEqualTo("B");
    }

    @Sql("/insert-members.sql")
    @Test
    void getMemberByName() {
        // when
        Member member = memberRepository.findByName("C").get(); // SELECT * FROM member WHERE name = 'C';

        // then
        assertThat(member.getId()).isEqualTo(6);
    }

    // 레코드 추가 -> save()
    @Test
    void saveMember() {
        // given
        Member member = new Member(1L, "A");

        // when
        memberRepository.save(member);  // INSERT INTO member (id, name) VALUES (1, 'A');

        // then
        assertThat(memberRepository.findById(1L).get().getName()).isEqualTo("A");
    }

    // 한꺼번에 여러 레코드 추가 -> saveAll()
    @Test
    void saveMembers() {
        // given
        List<Member> members = List.of(new Member(2L, "B"), new Member(3L, "C"));

        // when
        memberRepository.saveAll(members);  // INSERT INTO member (id, name) VALUESE (2, 'B'); INSERT INTO member (id, name) VALUESE (3, 'C');

        // then
        assertThat(memberRepository.findAll().size()).isEqualTo(3);
    }

    // 아이디로 레코드 삭제 -> deleteById()
    @Sql("/insert-members.sql")
    @Test
    void deleteMemberById() {
        // when
        memberRepository.deleteById(2L);    // DELETE FROM member WHERE id = 2;

        // then
        assertThat(memberRepository.findById(2L).isEmpty()).isTrue();
    }

    // 모든 레코드 삭제 -> deleteAll()
    @Sql("/insert-members.sql")
    @Test
    void deleteAll() {
        // when
        memberRepository.deleteAll();   // DELETE FROM member;

        // then
        assertThat(memberRepository.findAll().size()).isZero();
    }
}