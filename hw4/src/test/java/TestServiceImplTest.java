import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;
import ru.otus.hw.service.LocalizedIOService;
import ru.otus.hw.service.TestServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {TestServiceImpl.class})
public class TestServiceImplTest {
    @MockBean
    private QuestionDao questionDao;
    @MockBean
    private LocalizedIOService localizedIOService;

    private TestServiceImpl testService;

    @Test
    void test(){
        testService = new TestServiceImpl(localizedIOService, questionDao);
        given(questionDao.findAll()).willReturn(List.of(new Question("Is UTest?"
                ,List.of(new Answer("yes", true)))));
        given(localizedIOService.readIntForRange(1,1,null)).willReturn(1);
        TestResult testResult = testService.executeTestFor(new Student("Test","Test"));
        assertThat(testResult).isNotNull();
        assertThat(testResult.getAnsweredQuestions().size()).isEqualTo(1);
        assertThat(testResult.getRightAnswersCount()).isEqualTo(1);
    }
}
