package com.dkharchenko_hillel.homework1;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class ResourceReader {
    @Setter
    private ClassPathResource resource;
    private List<List<String>> records;
    private final static String CORRECT_ANSWER_PATTERN = " - правильный ответ";

    public void readResource() {
        records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getPatternedElements() {
        List<String> correctAnswers = new ArrayList<>();
        records.forEach(record -> record.forEach(s -> {
            if (s.contains(CORRECT_ANSWER_PATTERN)) {
                correctAnswers.add(s.replace(CORRECT_ANSWER_PATTERN, ""));
                record.set(record.indexOf(s), s.replace(CORRECT_ANSWER_PATTERN, ""));
            }
        }));
        return correctAnswers;
    }

    public String getFormattedOutput(List<String> list) {
        StringBuilder builder = new StringBuilder();
        list.forEach(s -> builder.append(s).append(","));
        builder.deleteCharAt(builder.lastIndexOf(",")).deleteCharAt(builder.indexOf(","));
        return builder.toString();
    }
}
