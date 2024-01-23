package quiz.panelsframes;

import org.hibernate.Session;
import quiz.Capital;
import quiz.City;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MyFrame implements QuizCallback {

    public static final int FRAME_WIDTH = 650;
    private static int score = 0;
    public static boolean correct = false;
    private Session session;
    private List<City> cities;
    private List<Capital> capitals;
    private QuestionPanel questionPanel;
    private RadiobuttonPanel panel;
    private String currentQuestion;
    private List<Capital> currentAnswerOptions;
    private FrameSkeleton theFrame;

    public MyFrame(FrameSkeleton theFrame, String questionText, List<Capital> answerOptions, Capital correctAnswer, Session session, List<City> cities, List<Capital> capitals) {
        this.theFrame = theFrame;
        // this.theFrame.setVisible(false);
        this.session = session;
        this.cities = cities;
        this.capitals = capitals;
        this.currentQuestion = questionText;
        this.currentAnswerOptions = answerOptions;

        displayFrame(questionText, answerOptions, correctAnswer);
    }

    private void loadNextQuestion() {
        WindowEngine.loadNextQuestion(session, cities, capitals);
    }

    @Override
    public void onQuestionAnswered(boolean isCorrect) {

        if (isCorrect) {
            score++;
            correct = true;
            System.out.println("Correct answer! Setting correct flag to true.");

        }

        if (WindowEngine.getCurrentQuestionNumber() < WindowEngine.getTotalQuestions()) {
            delayAndUpdateFrame();
        } else if (WindowEngine.getCurrentQuestionNumber() == WindowEngine.getTotalQuestions()) {
            delayAndUpdateFrameLastQuestion();
        } else {
            displayScoreAndClose();
        }
    }

    private void displayFrame(String questionText, List<Capital> answerOptions, Capital correctAnswer) {

        questionPanel = new QuestionPanel(questionText);
        panel = new RadiobuttonPanel(answerOptions, correctAnswer, this);
        BackgroundPanel backgroundPanel = new BackgroundPanel("src/main/resources/background.jpg");

        this.theFrame.setTitle("Guess the capital");
        this.theFrame.setSize(FRAME_WIDTH, 400);

        this.theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.theFrame.setLayout(new BorderLayout());

        this.theFrame.setContentPane(backgroundPanel);
        this.theFrame.add(questionPanel, BorderLayout.NORTH);
        this.theFrame.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.CENTER); // Adjust the height as needed
        this.theFrame.add(panel, BorderLayout.CENTER);

        this.theFrame.setVisible(true);
    }

    public void updateFrame() {
        this.theFrame.getContentPane().removeAll();
        this.theFrame.questionPanel = new QuestionPanel(currentQuestion);
        this.theFrame.panel = new RadiobuttonPanel(currentAnswerOptions, null, this);
        this.theFrame.add(questionPanel, BorderLayout.NORTH);
        this.theFrame.add(Box.createRigidArea(new Dimension(20, 20)), BorderLayout.CENTER);
        this.theFrame.add(panel, BorderLayout.CENTER);
        this.theFrame.revalidate();
        this.theFrame.repaint();
        System.out.println("Checking correct flag: " + correct);
    }

    private void delayAndUpdateFrame() {

        if (correct) {
            displayNotification("Correct", 1500);
        }
        if (!correct) {
            displayNotification("Better luck next time", 1500);
        }

        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFrame();
                loadNextQuestion();
            }
        });
        timer.setRepeats(false);
        timer.start();
        correct = false;
    }

    private void delayAndUpdateFrameLastQuestion() {

        if (correct) {
            displayNotification("Correct", 1500);
        }
        if (!correct) {
            displayNotification("Better luck next time", 1500);
        }
        correct = false;
        displayScoreAndClose();
    }

    private void displayNotification(String message, int duration) {
        AutoClosePopupPanel autoClosePanel = new AutoClosePopupPanel(message, duration);

        JDialog dialog = new JDialog(this.theFrame, "Notification", Dialog.ModalityType.MODELESS);
        dialog.setUndecorated(true);
        dialog.getContentPane().add(autoClosePanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this.theFrame);
        dialog.setVisible(true);
    }

    private void displayScoreAndClose() {

        Timer timer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayDialog();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void displayDialog() {
        JOptionPane.showMessageDialog(this.theFrame, "Your final score: " + score + "/5");
        System.exit(0);
    }

}

