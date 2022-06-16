package com.training.service;

import com.training.exceptions.FileUploadException;
import com.training.models.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Movie> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Movie> movieList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Movie movie = Movie.builder()
                        .title(csvRecord.get("title"))
                        .budget(csvRecord.get("budget"))
                        .year(csvRecord.get("year"))
                        .review(csvRecord.get("review"))
                        .country(csvRecord.get("country"))
                        .genre(csvRecord.get("genre"))
                        .duration(csvRecord.get("duration"))
                        .build();
                movieList.add(movie);
            }
            return movieList;
        } catch (IOException e) {
            throw new FileUploadException(e.getMessage());
        }
    }
}
