package ru.otus.hw.test;

import org.junit.jupiter.api.Test;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyTest {

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
