import org.junit.jupiter.api.*;

public class JUnitCircleTest {
    /*
     * @BeforeAll
     * 전체 테스트 시작 전 처음으로 1회 실행
     * e.g., 데이터베이스 연결 혹은 테스트 환경 초기화할 때 사용
     * 이 애너테이션은 전체 테스트 실행 주기에서 한 번만 호출되므로 static으로 선언해야 함
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    /*
     * @BeforeEach
     * 테스트 케이스 시작하기 전에 매번 실행
     * e.g., 테스트 메서드에서 사용하는 객체 초기화 혹은 테스트에 필요한 값 미리 넣을 때 사용
     * 각 인스턴스에 대해 메서드 호출해야 하므로 static이 아니어야 함
     */
    @BeforeEach
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }

    /*
     * @AfterAll
     * 전체 테스트를 마치고 종료하기 전에 1회 실행
     * e.g., 데이터베이스 연결 종료 혹은 공통적으로 사용하는 자원 해제할 때 사용
     * 전체 테스트 실행 주기에서 한 번만 호출되므로 static으로 선언해야 함
     */
    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    /*
     * @AfterEach
     * 각 테스트 케이스를 종료하기 전 매번 실행
     * e.g., 테스트 이후에 특정 데이터 삭제해야 하는 경우 사용
     * @BeforeEach 애너테이션과 마찬가지로 메서드는 static이 아니어야 함
     */
    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach");
    }
}
