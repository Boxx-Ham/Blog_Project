import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class JUnitQuiz {
    /*
     * AssertJ
     * AssertJ는 JUnit과 함께 사용해 검증문의 가독성을 확 높여주는 라이브러리
     * 
     * Asserttion vs AssertJ
     * Assertions.assertEquals(sum, a + b); -> 기댓값과 비교값이 잘 구분되지 않음
     * AssertThat(a + b).isEqualTo(sum); -> 기댓값과 비교값이 명확히 구분됨
     */

    /*
     * AssertJ에서 자주 사용하는 메서드
     * isEqualTo(A) : A 값과 같은지 검즘
     * isNotEqualTo(A) : A 값과 다른지 검증
     * contains(A) : A 값을 포함하는지 검증
     * doesNotContain(A) : A 값을 포함하지 않는지 검증
     * startsWith(A) : A 값으로 시작하는지 검증
     * endsWith(A) : A 값으로 끝나는지 검증
     * isEmtpy() : 비어 있는 값인지 검증
     * isNotEmpty() : 비어 있지 않은 값인지 검증
     * isPositive() : 양수인지 검증
     * isNegative() : 음수인지 검증
     * isGreaterThan(1) : 1보다 큰 값인지 검증
     * isLessThan(1) : 1보다 작은 값인지 검증
     */

    @Test
    public void junitTest() {
        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = "홍길순";

        // 1. 모든 변수가 null이 아닌지 확인
        assertThat(name1).isNotNull();
        assertThat(name2).isNotNull();
        assertThat(name3).isNotNull();
        
        // 2. name1과 name2가 같은지 확인
        assertThat(name1).isEqualTo(name2);

        // 3. name1과 name3이 다른지 확인
        assertThat(name1).isNotEqualTo(name3);
    }    
}
