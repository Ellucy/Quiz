package quiz.panelsframes;

import org.hibernate.Session;
import quiz.Capital;
import quiz.City;
import quiz.Engine;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyFrame extends JFrame implements QuizCallback {

    private int score = 0;
    private Session session;
    private List<City> cities;
    private List<Capital> capitals;

    public MyFrame(String questionText, List<Capital> answerOptions, Capital correctAnswer, Session session, List<City> cities, List<Capital> capitals) {

        QuestionPanel questionPanel = new QuestionPanel(questionText);
        RadiobuttonPanel panel = new RadiobuttonPanel(answerOptions, correctAnswer, this);
        BackgroundPanel backgroundPanel = new BackgroundPanel("src/main/resources/background.jpg");


        this.setTitle("Guess the capital");
        this.setSize(600, 400);
        this.cities = cities;
        this.capitals = capitals;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        this.setContentPane(backgroundPanel);
        this.add(questionPanel, BorderLayout.NORTH);
        this.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.CENTER); // Adjust the height as needed
        this.add(panel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    @Override
    public void onQuestionAnswered(boolean isCorrect) {

        if (isCorrect) {
            score++;
            System.out.println("Here");
        }

        if (score <= 5) {
            System.out.println("HEre");
            System.out.println(score);
            System.out.println("Cities: " + cities);
            System.out.println("Capitals: " + capitals);
            Engine.loadNextQuestion(session, cities, capitals);
        } else {
            // Display the final score
            JOptionPane.showMessageDialog(this, "Your final score: " + score + "/5");
            System.exit(0);
        }
    }
}

