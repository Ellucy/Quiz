package quiz.panelsframes;

import quiz.Capital;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyFrame extends JFrame {

    public MyFrame(String questionText, List<String> answerOptions, Capital correctAnswer) {

        QuestionPanel questionPanel = new QuestionPanel(questionText);
        RadiobuttonPanel panel = new RadiobuttonPanel(answerOptions, correctAnswer);
        BackgroundPanel backgroundPanel = new BackgroundPanel("src/main/resources/background.jpg");


        this.setTitle("Guess the capital");
        this.setSize(600, 400);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        this.setContentPane(backgroundPanel);
        this.add(questionPanel, BorderLayout.NORTH);
        this.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.CENTER); // Adjust the height as needed
        this.add(panel, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
