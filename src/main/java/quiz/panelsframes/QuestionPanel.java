package quiz.panelsframes;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {

    private String question;

    QuestionPanel(String question) {
        this.question = question;
        Font font = new Font("Arial", Font.BOLD, 36);
        setFont(font);
        this.setPreferredSize(new Dimension(MyFrame.FRAME_WIDTH, 60));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        FontMetrics fontMetrics = g.getFontMetrics();
        int x = (getWidth() - fontMetrics.stringWidth(question)) / 2;
        int y = (getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();

        g.drawString(question, x, y);
    }
}

