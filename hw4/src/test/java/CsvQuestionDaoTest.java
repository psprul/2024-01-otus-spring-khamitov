import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.dao.QuestionDao;

import static org.assertj.core.api.Assertions.assertThatList;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {CsvQuestionDaoTest.class})
public class CsvQuestionDaoTest {
    @Mock
    private AppProperties appProperties;

    private QuestionDao questionDao;

    @Test
    void test(){
        questionDao = new CsvQuestionDao(appProperties);
        given(appProperties.getTestFileName()).willReturn("questions.csv");
        assertThatList(questionDao.findAll())
                .isNotNull()
                .isNotEmpty()
                .hasSize(5);
    }
}
