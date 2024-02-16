package ru.otus.hw.test;

import org.junit.jupiter.api.Test;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.TestServiceImpl;

class TestServiceImplTest {
    @Test
    void Test(){
        AppProperties appProperties = new AppProperties("questions.csv");
        CsvQuestionDao csvQuestionDao = new CsvQuestionDao(appProperties);
        TestServiceImpl testService = new TestServiceImpl(new IOService() {
            @Override
            public void printLine(String s) {
            }

            @Override
            public void printFormattedLine(String s, Object... args) {
            }
        }, csvQuestionDao);
        testService.executeTest();
    }
}
