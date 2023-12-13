import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeGame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);

            LoginScreen loginScreen = new LoginScreen(frame);
            frame.add(loginScreen);


            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    loginScreen.adjustImageSize();
                }
            });

            frame.setVisible(true);
        });
    }
}






