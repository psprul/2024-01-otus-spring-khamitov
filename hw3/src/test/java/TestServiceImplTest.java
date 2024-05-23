import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.LocalizedIOService;
import ru.otus.hw.service.TestServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TestServiceImplTest {
    @Mock
    private QuestionDao questionDao;
    @Mock
    LocalizedIOService localizedIOService;

    TestServiceImpl testService;

    @Test
    void test(){
        testService = new TestServiceImpl(localizedIOService, questionDao);

        TestResult testResult = testService.executeTestFor(new Student("Test","Test"));
        assertThat(testResult).isNotNull();
    }
}
