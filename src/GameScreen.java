import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements ActionListener, KeyListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private Timer timer;
    private Snake snake;
    private Direction currentDirection;
    private Food food;

    public GameScreen(JFrame frame) {
        setLayout(new BorderLayout());
        setFocusable(true);
        addKeyListener(this);

        snake = new Snake();
        currentDirection = Direction.RIGHT;
        food = new Food(); // Yeni bir yem oluştur

        timer = new Timer(100, this);
        timer.start();
    }

    public void startGame() {
        // Oyun başladığında gerekirse ek işlemler buraya eklenebilir.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move(currentDirection);

        // Yılanın yemi yemesini kontrol et
        if (snakeCollidesWithFood()) {
            food.generateFood(); // Yeni bir yem oluştur
            // Yılanın boyutunu veya puanını artırabilirsiniz
        }

        repaint();
    }

    private boolean snakeCollidesWithFood() {
        // Yılanın yem ile çarpışmasını kontrol et
        int snakeX = snake.getX();
        int snakeY = snake.getY();
        int foodX = food.getX();
        int foodY = food.getY();
        int foodSize = food.getSize();

        return snakeX < foodX + foodSize &&
                snakeX + snake.getSize() > foodX &&
                snakeY < foodY + foodSize &&
                snakeY + snake.getSize() > foodY;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        snake.draw(g);
        food.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                currentDirection = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                currentDirection = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                currentDirection = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                currentDirection = Direction.RIGHT;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
