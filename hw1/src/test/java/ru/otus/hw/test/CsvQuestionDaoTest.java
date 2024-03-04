package ru.otus.hw.test;

import org.junit.jupiter.api.Test;
import ru.otus.hw.config.AppProperties;
import ru.otus.hw.dao.CsvQuestionDao;

class CsvQuestionDaoTest {
    @Test
    void Test(){
        AppProperties appProperties = new AppProperties("questions.csv");
        CsvQuestionDao csvQuestionDao = new CsvQuestionDao(appProperties);
        csvQuestionDao.findAll();
    }
}
