package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printLineLocalized("TestService.answer.the.questions");
        ioService.printLine("");

        var questions = questionDao.findAll();
        var testResult = new TestResult(student);
        int answerNumber;

        for (var question: questions) {
            ioService.printFormattedLine(ioService.getMessage("TestService.question"),question.text());
            for (Answer answer : question.answers()) {
                ioService.printFormattedLine(ioService.getMessage("TestService.answer"),question.answers().indexOf(answer) + 1,answer.text());
            }
            answerNumber = ioService.readIntForRange(1,question.answers().size(), ioService.getMessage("TestService.answer.check.number",question.answers().size()));
            testResult.applyAnswer(question, question.answers().get(answerNumber - 1).isCorrect());
            ioService.printLine("");
        }

        return testResult;
    }

}
