package com.dkharchenko_hillel.homework1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        NameVerificationService nameVerificationService = (NameVerificationService) context.getBean("nameVerificationService");
        QuizService quizService = (QuizService) context.getBean("quizService");

        nameVerificationService.askNameAndLastname();
        quizService.executeQuiz();
    }
}
