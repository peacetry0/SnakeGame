import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

class GameScreen extends JPanel {

    private JFrame frame;
    private Timer timer;
    private ArrayList<Point> snake; // Yılanın vücut segmentleri
    private int foodX, foodY;
    private boolean isGameOver;
    private Direction direction;

    public GameScreen(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setFocusable(true);
        addKeyListener(new GameKeyListener());

        initializeGame();

        // Start button ekleyelim
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        // Butonu ekleyelim
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initializeGame() {
        snake = new ArrayList<>();
        snake.add(new Point(100, 100));
        snake.add(new Point(80, 100));
        snake.add(new Point(60, 100));

        placeFood();
        isGameOver = false;
        direction = Direction.RIGHT;
    }

    public void startGame() {
        timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isGameOver) {
                    moveSnake();
                    checkCollisions();
                    repaint();
                } else {
                    timer.stop();
                    showGameOverDialog();
                }
            }
        });
        timer.start();
    }

    private void moveSnake() {
        Point head = snake.get(0);
        Point newHead = new Point(head);

        switch (direction) {
            case UP:
                newHead.y -= 20;
                break;
            case DOWN:
                newHead.y += 20;
                break;
            case LEFT:
                newHead.x -= 20;
                break;
            case RIGHT:
                newHead.x += 20;
                break;
        }

        snake.add(0, newHead);

        // Yılanın uzunluğunu kontrol et ve gerekiyorsa son segmenti kaldır
        if (snake.size() > 3) {
            snake.remove(snake.size() - 1);
        }
    }

    private void checkCollisions() {
        checkFoodCollision();
        checkWallCollision();
        checkSelfCollision();
    }

    private void checkFoodCollision() {
        if (snake.get(0).equals(new Point(foodX, foodY))) {
            placeFood();
        }
    }

    private void checkWallCollision() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= getWidth() || head.y < 0 || head.y >= getHeight()) {
            isGameOver = true;
        }
    }

    private void checkSelfCollision() {
        Point head = snake.get(0);
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                isGameOver = true;
                break;
            }
        }
    }

    private void placeFood() {
        Random random = new Random();

        // Oyun alanının genişliği ve yüksekliği pozitif olmalı
        int width = getWidth();
        int height = getHeight();

        if (width <= 0 || height <= 0) {
            // Eğer genişlik veya yükseklik negatif veya sıfırsa, varsayılan değerleri kullan
            width = 800; // Varsayılan genişlik
            height = 600; // Varsayılan yükseklik
        }

        foodX = random.nextInt(width / 20) * 20;
        foodY = random.nextInt(height / 20) * 20;
    }

    private void showGameOverDialog() {
        JOptionPane.showMessageDialog(this, "Game Over! Your score: " + (snake.size() - 3));
    }

    private class GameKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    direction = Direction.UP;
                    break;
                case KeyEvent.VK_DOWN:
                    direction = Direction.DOWN;
                    break;
                case KeyEvent.VK_LEFT:
                    direction = Direction.LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                    direction = Direction.RIGHT;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}

