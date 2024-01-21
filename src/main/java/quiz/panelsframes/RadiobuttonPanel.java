package quiz.panelsframes;

import quiz.Capital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class RadiobuttonPanel extends JPanel implements ActionListener {

    JRadioButton firstButton;
    JRadioButton secondButton;
    JRadioButton thirdButton;
    JRadioButton fourthButton;
    private Capital correctAnswer;

    RadiobuttonPanel(List<String> answerOptions, Capital correctAnswer) {

        this.correctAnswer = correctAnswer;
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        setPreferredSize(new Dimension(600, 150));

        firstButton = new JRadioButton(answerOptions.get(0));
        secondButton = new JRadioButton(answerOptions.get(1));
        thirdButton = new JRadioButton(answerOptions.get(2));
        fourthButton = new JRadioButton(answerOptions.get(3));

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

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton selectedButton = (JRadioButton) e.getSource();

//        System.out.println("Selected: " + selectedButton.getText());
//        System.out.println(correctAnswer.getCapitalName());

        if (selectedButton.getText().equals(correctAnswer.getCapitalName())) {
            System.out.println("Correct value");
        } else {
            System.out.println("Sorry, you missed");
        }
    }
}
