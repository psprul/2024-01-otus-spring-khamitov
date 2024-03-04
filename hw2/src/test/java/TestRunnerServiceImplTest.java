import org.junit.jupiter.api.Test;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;
import ru.otus.hw.service.*;

public class TestRunnerServiceImplTest {
    @Test
    void Test(){
        
        TestRunnerServiceImpl testRunnerService = new TestRunnerServiceImpl(
                new TestService() {
                    @Override
                    public TestResult executeTestFor(Student student) {
                        return null;
                    }
                }
                , new StudentService() {
            @Override
            public Student determineCurrentStudent() {
                return null;
            }
        }
                , new ResultService() {
                    @Override
                    public void showResult(TestResult testResult) {

                    }
                }
        );
        testRunnerService.run();
    }
}
