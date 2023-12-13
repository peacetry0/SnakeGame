import java.awt.*;

public class Snake {

    private int x;
    private int y;
    private int size;

    public Snake() {
        // Yılanın başlangıç pozisyonu ve boyutu
        x = 50;
        y = 50;
        size = 20;
    }

    public void move(Direction direction) {
        // Yılanın hareketi burada işlenir
        // Gerekirse yılanın hareketine yönelik kodlar buraya eklenir
        switch (direction) {
            case UP:
                y -= size;
                break;
            case DOWN:
                y += size;
                break;
            case LEFT:
                x -= size;
                break;
            case RIGHT:
                x += size;
                break;
        }
    }

    public void draw(Graphics g) {
        // Yılanın çizimi burada yapılır
        g.setColor(Color.GREEN);
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
