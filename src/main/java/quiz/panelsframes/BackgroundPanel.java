package quiz.panelsframes;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private Image backgroundImage;

    BackgroundPanel(String imagePath) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        backgroundImage = imageIcon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

