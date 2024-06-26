package ru.otus.hw.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {
        List<QuestionDto> questionDtoList = null;
        try {
            URL resource = getClass().getClassLoader().getResource(fileNameProvider.getTestFileName());
            if (resource == null) {
                throw new IllegalArgumentException("file not found!");
            }
            FileReader fileReader = new FileReader(new File(resource.toURI()));
            questionDtoList = new CsvToBeanBuilder<QuestionDto>(fileReader)
                    .withType(QuestionDto.class)
                    .withSkipLines(1)
                    .withSeparator(';')
                    .build()
                    .parse();
        } catch (IllegalStateException | URISyntaxException | IOException e) {
            throw new QuestionReadException("Error = ",e);
        }

        return new ArrayList<>(questionDtoList.stream().map(QuestionDto::toDomainObject).toList());
    }
}
