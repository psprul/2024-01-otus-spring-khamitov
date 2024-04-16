import org.junit.jupiter.api.Test;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.domain.Student;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.TestServiceImpl;

public class TestServiceImplTest {
    @Test
    void test(){
        TestServiceImpl testService = new TestServiceImpl(new IOService() {
            @Override
            public void printLine(String s) {

            }

            @Override
            public void printFormattedLine(String s, Object... args) {

            }

            @Override
            public String readString() {
                return null;
            }

            @Override
            public String readStringWithPrompt(String prompt) {
                return null;
            }

            @Override
            public int readIntForRange(int min, int max, String errorMessage) {
                return 1;
            }

            @Override
            public int readIntForRangeWithPrompt(int min, int max, String prompt, String errorMessage) {
                return 0;
            }
        }, new CsvQuestionDao(new TestFileNameProvider() {
            @Override
            public String getTestFileName() {
                return "questions.csv";
            }
        }));
        testService.executeTestFor(new Student("Test","Test"));
    }
}
