package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);
        int answerNumber;

        for (var question: questions) {
            ioService.printFormattedLine("Question = %s",question.text());
            for (Answer answer : question.answers()) {
                ioService.printFormattedLine("%s answer = %s",question.answers().indexOf(answer) + 1,answer.text());
            }
            answerNumber = ioService.readIntForRange(1,question.answers().size(),
                    "Enter a value from 1 to " + question.answers().size());
            testResult.applyAnswer(question, question.answers().get(answerNumber - 1).isCorrect());
            ioService.printLine("");
        }
        return testResult;
    }
}
