package com.dkharchenko_hillel.homework1;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class NameVerificationService {
    private User user;

    public void askNameAndLastname() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter your firstname:");
        user.setFirstName(scanner.nextLine());
        checkIfCorrect(user.getFirstName());

        System.out.println("Please, enter your lastname:");
        user.setLastName(scanner.nextLine());
        checkIfCorrect(user.getLastName());
    }

    private void checkIfCorrect(String string) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);
        if (string.equals("") || matcher.lookingAt()) {
            throw new IllegalArgumentException("Wrong personal info");
        }
    }
}
