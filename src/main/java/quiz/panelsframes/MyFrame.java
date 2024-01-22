package quiz.panelsframes;

import org.hibernate.Session;
import quiz.Capital;
import quiz.City;
import quiz.Engine;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyFrame extends JFrame implements QuizCallback {

    private static int score = 0;
    private Session session;
    private List<City> cities;
    private List<Capital> capitals;
    private QuestionPanel questionPanel;
    private RadiobuttonPanel panel;
    private String currentQuestion;
    private List<Capital> currentAnswerOptions;

    public MyFrame(String questionText, List<Capital> answerOptions, Capital correctAnswer, Session session, List<City> cities, List<Capital> capitals) {

        this.session = session;
        this.cities = cities;
        this.capitals = capitals;
        this.currentQuestion = questionText;
        this.currentAnswerOptions = answerOptions;


        questionPanel = new QuestionPanel(questionText);
        panel = new RadiobuttonPanel(answerOptions, correctAnswer, this);
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

    private void loadNextQuestion() {
        WindowEngine.loadNextQuestion(session, cities, capitals);
    }

    @Override
    public void onQuestionAnswered(boolean isCorrect) {

        if (isCorrect) {
            score++;
        }

        if (WindowEngine.getCurrentQuestionNumber() < WindowEngine.getTotalQuestions()) {
            loadNextQuestion();
            updateFrame();
        } else {
            // Display final score
            JOptionPane.showMessageDialog(this, "Your final score: " + score + "/5");
            System.exit(0);
        }
    }

    private void updateFrame() {
        this.getContentPane().removeAll();  // Remove existing components
        this.questionPanel = new QuestionPanel(currentQuestion);
        this.panel = new RadiobuttonPanel(currentAnswerOptions, null, this);
        this.add(questionPanel, BorderLayout.NORTH);
        this.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}

