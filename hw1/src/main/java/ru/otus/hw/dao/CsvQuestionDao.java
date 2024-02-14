package ru.otus.hw.dao;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.exceptions.QuestionReadException;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {
    private final TestFileNameProvider fileNameProvider;

    @Override
    public List<Question> findAll() {

        // Использовать CsvToBean
        // https://opencsv.sourceforge.net/#collection_based_bean_fields_one_to_many_mappings
        // Использовать QuestionReadException
        // Про ресурсы: https://mkyong.com/java/java-read-a-file-from-resources-folder/

        List<QuestionDto> questionDtoList = null;
        try(FileReader fileReader = new FileReader(new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileNameProvider.getTestFileName())).toURI()))) {
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
