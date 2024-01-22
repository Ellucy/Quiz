package quiz.panelsframes;

import lombok.Getter;
import org.hibernate.Session;
import quiz.Capital;
import quiz.City;
import quiz.QuizQuestion;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WindowEngine {

    private static final int TOTAL_QUESTIONS = 5;
    @Getter
    private static int currentQuestionNumber = 0;

    public static void playWindowGame(Session session, List<City> cities, List<Capital> capitals) {
        loadNextQuestion(session, cities, capitals);
    }

    public static void loadNextQuestion(Session session, List<City> cities, List<Capital> capitals) {

        if (currentQuestionNumber < TOTAL_QUESTIONS) {
            currentQuestionNumber++;
            Random random = new Random();

            City selectedCity = cities.get(random.nextInt(cities.size()));
            Capital correctCapital = selectedCity.getCapital();
            List<Capital> distractors = getDistractors(capitals, correctCapital);

            QuizQuestion quizQuestion = new QuizQuestion(
                    "What is the capital of " + selectedCity.getCityName() + "?",
                    correctCapital, distractors);

            // Display the new question using the GUI
            displayQuestion(session, quizQuestion, cities, capitals);
        } else {
            System.out.println("Game over!");
        }
    }

    private static void displayQuestion(Session session, QuizQuestion
            quizQuestion, List<City> cities, List<Capital> capitals) {
        // Shuffle the answer options
        List<Capital> options = quizQuestion.getAnswerOptions();
        Collections.shuffle(options);

        SwingUtilities.invokeLater(() -> {
            new MyFrame(quizQuestion.getQuestion(), options, quizQuestion.getCorrectAnswer(), session, cities, capitals);
        });
    }

    private static List<Capital> getDistractors(List<Capital> allCapitals, Capital correctCapital) {

        // Shuffle all capitals
        Collections.shuffle(allCapitals);

        // Remove correct capital
        allCapitals.remove(correctCapital);

        // Take three capitals as distractors
        return allCapitals.subList(0, 3);
    }
    public static int getTotalQuestions() {
        return TOTAL_QUESTIONS;
    }
}
