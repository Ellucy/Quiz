package quiz.panelsframes;

import javax.swing.*;
import java.awt.*;

public class AutoClosePopupPanel extends JPanel {

    public AutoClosePopupPanel(String message, int duration) {
        JLabel label = new JLabel(message);
        setPreferredSize(new Dimension(MyFrame.FRAME_WIDTH, 80));
        Font font = new Font("Arial", Font.PLAIN, 28);
        label.setFont(font);

        Timer timer = new Timer(duration, e -> {
            Window window = SwingUtilities.getWindowAncestor(AutoClosePopupPanel.this);
            if (window instanceof JDialog) {
                JDialog dialog = (JDialog) window;
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();

        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
    }
}
