package quiz.panelsframes;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {

        QuestionPanel questionPanel = new QuestionPanel("What is the capital of France?");
        RadiobuttonPanel panel = new RadiobuttonPanel();
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
