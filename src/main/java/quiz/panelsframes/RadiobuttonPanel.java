package quiz.panelsframes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadiobuttonPanel extends JPanel implements ActionListener {

    JRadioButton capitalButton;
    JRadioButton distractorButton1;
    JRadioButton distractorButton2;
    JRadioButton distractorButton3;

    RadiobuttonPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        setPreferredSize(new Dimension(600, 150));

        capitalButton = new JRadioButton("Paris");
        distractorButton1 = new JRadioButton("Tallinn");
        distractorButton2 = new JRadioButton("Tokyo");
        distractorButton3 = new JRadioButton("Rome");

        Font font = new Font("Arial", Font.PLAIN, 28);

        capitalButton.setFont(font);
        distractorButton1.setFont(font);
        distractorButton2.setFont(font);
        distractorButton3.setFont(font);

        ButtonGroup group = new ButtonGroup();
        group.add(capitalButton);
        group.add(distractorButton1);
        group.add(distractorButton2);
        group.add(distractorButton3);

        this.add(capitalButton);
        this.add(distractorButton1);
        this.add(distractorButton2);
        this.add(distractorButton3);

        capitalButton.addActionListener(this);
        distractorButton1.addActionListener(this);
        distractorButton2.addActionListener(this);
        distractorButton3.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == capitalButton) {
            System.out.println("Correct value");
        } else {
            System.out.println("Sorry, you missed");
        }
    }
}
