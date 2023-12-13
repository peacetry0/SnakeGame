import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JPanel {

    private JFrame frame;
    private ImageIcon backgroundIcon;
    private JLabel backgroundLabel;

    public LoginScreen(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        // Background image için yolun doğru olup olmadığını kontrol et
        String imagePath = "/resources/LoginScreenImage.png";
        backgroundIcon = new ImageIcon(getClass().getResource(imagePath));
        if (backgroundIcon.getImage() == null) {
            System.err.println("Image not found: " + imagePath);
            return;
        }

        // Using JLabel to add a background image
        backgroundLabel = new JLabel(backgroundIcon);
        add(backgroundLabel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();  // Oyun ekranını oluştur ve JFrame içeriğini değiştir
            }
        });

        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Pencere boyutu değiştiğinde arkaplan resminin boyutunu ayarla
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                adjustImageSize();
            }
        });
    }

    public  void startGame() {
        // Oyun ekranını oluştur
        GameScreen gameScreen = new GameScreen(frame);

        // JFrame içeriğini değiştir
        frame.getContentPane().removeAll();
        frame.add(gameScreen);
        frame.revalidate();

        // Oyunu başlat
        gameScreen.startGame();
    }

    public void adjustImageSize() {
        // Pencere boyutuna göre arkaplan resminin boyutunu ayarla
        int width = frame.getWidth();
        int height = frame.getHeight();

        Image scaledImage = backgroundIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        backgroundLabel.setIcon(scaledIcon);
    }
}
