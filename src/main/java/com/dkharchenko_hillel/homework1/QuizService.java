package com.dkharchenko_hillel.homework1;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuizService {
    @Getter
    @Setter
    private ClassPathResource resource;
    private List<List<String>> records;
    private int score;

    public void executeQuiz() {
        try {
            readQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> answers = getCorrectAnswers();
        for (List<String> record : records) {
            askQuestion(record, answers.get(records.indexOf(record)));
        }
        System.out.println("Your score is: " + score + "/" + records.size());
    }

    private void askQuestion(List<String> question, String rightAnswer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the right answer:");
        System.out.println(getFormedQuestionOutput(question));
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(rightAnswer.trim())) {
            score++;
            System.out.println("Correct answer!" + "\n");
        } else {
            System.out.println("Wrong answer!" + "\n");
        }
    }

    private String getFormedQuestionOutput(List<String> question) {
        StringBuilder builder = new StringBuilder();
        for (String s : question) {
            builder.append(s).append(",");
        }
        builder.deleteCharAt(builder.lastIndexOf(",")).deleteCharAt(builder.indexOf(","));
        return builder.toString();
    }

    private List<String> getCorrectAnswers() {
        List<String> correctAnswers = new ArrayList<>();
        for (List<String> record : records) {
            for (String s : record) {
                if(s.contains(" - правильный ответ")) {
                    correctAnswers.add(s.replace(" - правильный ответ", ""));
                    record.set(record.indexOf(s), s.replace(" - правильный ответ", ""));
                }
            }
        }
        return correctAnswers;
    }

    private void readQuestions() throws IOException {
        records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
    }
}
