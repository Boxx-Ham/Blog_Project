import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUintCycleQuiz {
    
    // 각 테스트 마다 Hello! 출력 -> @BeforeEach
    @BeforeEach
    public void beforeEach() {
        System.out.println("Hello!");
    }

    // 제일 마지막 테스트 실행 후 Bye! 출력 -> @AfterAll
    @AfterAll
    public static void afterAll() {
        System.out.println("Bye!");
    }

    @Test
    public void junitQuiz3() {
        System.out.println("This is first test");
    }

    @Test
    public void junitQuiz4() {
        System.out.println("This is second test");
    }
}
