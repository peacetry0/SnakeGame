import java.awt.*;
import java.util.Random;

public class Food {

    private int x;
    private int y;
    private int size;
    private Random random;

    public Food() {
        size = 20;
        random = new Random();
        generateFood();
    }

    public void generateFood() {
        // Yemi rastgele bir konuma yerleştir
        x = random.nextInt(GameScreen.WIDTH - size);
        y = random.nextInt(GameScreen.HEIGHT - size);
    }

    public void draw(Graphics g) {
        // Yemin çizimi burada yapılır
        g.setColor(Color.RED);
        g.fillRect(x, y, size, size);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}
