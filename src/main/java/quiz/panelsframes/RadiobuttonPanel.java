package quiz.panelsframes;

import quiz.Capital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RadiobuttonPanel extends JPanel implements ActionListener {

    JRadioButton firstButton;
    JRadioButton secondButton;
    JRadioButton thirdButton;
    JRadioButton fourthButton;

    private Capital correctAnswer;
    private String selectedAnswer;
    protected QuizCallback quizCallback;
    private List<Capital> answerOptions;


    RadiobuttonPanel(List<Capital> answerOptions, Capital correctAnswer, QuizCallback quizCallback) {

        this.answerOptions = answerOptions;
        this.correctAnswer = correctAnswer;
        this.quizCallback = quizCallback;

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        setPreferredSize(new Dimension(MyFrame.FRAME_WIDTH, 150));

        firstButton = new JRadioButton(answerOptions.get(0).getCapitalName());
        secondButton = new JRadioButton(answerOptions.get(1).getCapitalName());
        thirdButton = new JRadioButton(answerOptions.get(2).getCapitalName());
        fourthButton = new JRadioButton(answerOptions.get(3).getCapitalName());

        Font font = new Font("Arial", Font.PLAIN, 28);

        firstButton.setFont(font);
        secondButton.setFont(font);
        thirdButton.setFont(font);
        fourthButton.setFont(font);

        ButtonGroup group = new ButtonGroup();
        group.add(firstButton);
        group.add(secondButton);
        group.add(thirdButton);
        group.add(fourthButton);

        this.add(firstButton);
        this.add(secondButton);
        this.add(thirdButton);
        this.add(fourthButton);

        firstButton.addActionListener(this);
        secondButton.addActionListener(this);
        thirdButton.addActionListener(this);
        fourthButton.addActionListener(this);

        add(firstButton);
        add(secondButton);
        add(thirdButton);
        add(fourthButton);


        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton selectedButton = (JRadioButton) e.getSource();
        selectedAnswer = selectedButton.getText();

        Capital selectedCapital = answerOptions.stream()
                .filter(capital -> capital.getCapitalName().equals(selectedAnswer))
                .findFirst()
                .orElse(null);

        if (selectedCapital != null && selectedCapital.equals(correctAnswer)) {
            if (quizCallback != null) {
                quizCallback.onQuestionAnswered(true);
            }
        } else {
            quizCallback.onQuestionAnswered(false);
        }
    }
}
