import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class GameScene extends Scene{
    Rect background, foreground;
    Snake snake;
    KL keyListener;
    public Food food;
    public GameScene(KL keyListener){
        background = new Rect(0,0,Constraints.SCREEN_WIDTH,Constraints.SCREEN_HEIGHT);
        foreground = new Rect(24,48,Constraints.TILE_WIDTH*31,Constraints.TILE_WIDTH*22);
        snake = new Snake(10,48,48+24,24,24, foreground);
        this.keyListener = keyListener;
        food = new Food(foreground,snake,12,12,Color.red);
        food.spawn();
    }

    @Override
    public void update(double dt) {
        if (keyListener.isKeyPressed(KeyEvent.VK_UP)){
            snake.changeDirection(Direction.UP);
        }
        else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)){
            snake.changeDirection(Direction.DOWN);
        }
        if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)){
            snake.changeDirection(Direction.LEFT);
        }
        if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)){
            snake.changeDirection(Direction.RIGHT);
        }
        if (!food.isSpawned) food.spawn();
        food.update(dt);
        snake.update(dt);

    }
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(new Color(0x1B6F60));
        g2.fill(new Rectangle2D.Double(background.x,background.y,background.width,background.height));

        g2.setColor(new Color(0xF8C4F5));
        g2.fill(new Rectangle2D.Double(foreground.x,foreground.y,foreground.width,foreground.height));
        snake.draw(g2);
        food.draw(g2);


    }
}
