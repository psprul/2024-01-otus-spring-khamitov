package ru.otus.hw.test;

import org.junit.jupiter.api.Test;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.dao.CsvQuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.service.IOService;
import ru.otus.hw.service.TestServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyTest {


    @Test
    void CsvQuestionDaoTest(){
        AppProperties appProperties = new AppProperties("questions.csv");
        CsvQuestionDao csvQuestionDao = new CsvQuestionDao(appProperties);
        csvQuestionDao.findAll();
    }
    
    @Test
    void TestServiceImplTest(){
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

    @Test
    void testQuestionOneTrue(){
        List<Answer> answerList = new ArrayList<>(Collections.singleton(new Answer("Science doesn't know this yet", true)));
        Question question = new Question("Is there life on Mars?", answerList);

        Answer answerTrue = null;
        for (Answer answer : question.answers())
        {
            if (answer.isCorrect()) answerTrue = answer;
        }

        assertEquals("Science doesn't know this yet", answerTrue.text());

    }
}
