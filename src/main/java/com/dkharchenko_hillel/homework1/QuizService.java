package com.dkharchenko_hillel.homework1;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class QuizService {
    private ResourceReader resourceReader;
    private User user;
    private int score;

    public void executeQuiz() {
        checkUser();
        List<String> answers = resourceReader.getPatternedElements();
        resourceReader.getRecords().forEach(
                record -> askQuestion(record, answers.get(resourceReader.getRecords().indexOf(record))));
        System.out.println(user.getFirstName() + "\s" + user.getLastName()
                + " your score is: " + score + "/" + resourceReader.getRecords().size());
    }

    private void checkUser() {
        if (user.getFirstName() == null || user.getLastName() == null) {
            throw new IllegalArgumentException("Please, register user and try again");
        }
    }

    private void askQuestion(List<String> question, String rightAnswer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the right answer:");
        System.out.println(resourceReader.getFormattedOutput(question));
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase(rightAnswer.trim())) {
            score++;
            System.out.println("Correct answer!" + "\n");
        } else {
            System.out.println("Wrong answer!" + "\n");
        }
    }
}
