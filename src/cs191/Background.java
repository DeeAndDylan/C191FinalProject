package cs191;
import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    private Image backgroundImage;

    public Background(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
        setLayout(new BorderLayout());  // Use proper layout manager
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

