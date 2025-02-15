package boxx_ham.Blog_Project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
public class TestControllerTests {
    @Autowired
    protected MockMvc mockMvc; // MockMvc 주입

    @Autowired
    private WebApplicationContext context; // WebApplicationContext 주입

    @Autowired
    private MemberRepository memberRepository; // MemberRepository 주입

    @BeforeEach // 테스트 실행 전 실행하는 메서드
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach  // 테스트 실행 후 실행하는 메서드
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        // given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when
        // perform() 메서드 : 요청을 전송하는 역할을 하는 메서드
        // 결과로 ResultActions 객체를 받으며, ResultActions 객체는 반환값을 검증하고 확인하는 andExpect() 메서드 제공
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));   // accept() 메서드 : 요청을 보낼 때 무슨 타입으로 응답을 받을지 결정하는 메서드 -> JSON 타입으로 응답을 받겠다고 설정
        
        /*
         * http 주요 응답 코드
         * 200 OK : isOk() -> HTTP 응답 코드가 200 OK 인지 검증
         * 201 Created : isCreated() -> HTTP 응답 코드가 201 Created 인지 검증
         * 400 Bad Request : isBadRequest() -> HTTP 응답 코드가 400 Bad Request 인지 검증
         * 403 Forbidden : isForbidden() -> HTTP 응답 코드가 403 Forbidden 인지 검증
         * 404 Not Found : isNotFound() -> HTTP 응답 코드가 404 Not Found 인지 검증
         * 400번대 응답 코드 : is4xxClientError() -> HTTP 응답 코드가 400번대인지 검증
         * 500 Internal Server Error : isInternalServerError() -> HTTP 응답 코드가 500 Internal Server Error 인지 검증
         * 500번대 응답 코드 : is5xxServerError() -> HTTP 응답 코드가 500번대인지 검증
         */

        // then
        result
                .andExpect(status().isOk()) // andExpect() 메서드 : 응답 검증 -> 상태코드가 200인지 확인
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))  // jsonPach("$[0].${필드명}") : JSON 응답값을 검증하는 메서드 -> id 필드값이 저장된 값과 같은지 확인
                .andExpect(jsonPath("$[0].name").value(savedMember.getName())); // name 필드값이 저장된 값과 같은지 확인
    }
}
