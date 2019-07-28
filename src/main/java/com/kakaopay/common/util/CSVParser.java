package com.kakaopay.common.util;

import com.opencsv.CSVReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class CSVParser {

    public Optional<List<String[]>> parseFile(String fileName) throws IOException{
        ClassPathResource resource   = new ClassPathResource(fileName);
        FileReader        fileReader = new FileReader(resource.getFile());
        CSVReader         csvReader  = new CSVReader(fileReader);

        return  Optional.ofNullable(csvReader.readAll());
    }
}