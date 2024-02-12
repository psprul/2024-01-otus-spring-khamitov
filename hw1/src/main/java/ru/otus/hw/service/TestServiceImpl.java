package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @SuppressWarnings("checkstyle:LineLength")
    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        // Получить вопросы из дао и вывести их с вариантами ответов
        for (Question question : questionDao.findAll()) {
            ioService.printFormattedLine("Question = %s", new Object[]{question.text()});
            for (Answer answer : question.answers()) {
                ioService.printFormattedLine("Answer = %s , isCorrect = %b", answer.text(), answer.isCorrect());
            }
            ioService.printLine("");
        }
    }
}
