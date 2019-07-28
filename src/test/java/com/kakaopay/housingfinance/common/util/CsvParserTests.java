package com.kakaopay.housingfinance.common.util;

import com.kakaopay.common.util.CSVParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CsvParserTests {

    @InjectMocks
    CSVParser csvParser;

    @Test
    public void testCsvParserNormalSuccesss(){
        String fileName = "housing_provision.csv";
        Optional<List<String[]>> result;

        try{
            result = csvParser.parseFile(fileName);

            assertThat(result.isPresent()).isEqualTo(true);
            assertThat(result).isNotEmpty();
            assertThat(result.get().get(0)).containsAnyOf("연도");
        }catch (Exception e) {
           assertThat(e).isEqualTo(IOException.class);
        }
    }
}
