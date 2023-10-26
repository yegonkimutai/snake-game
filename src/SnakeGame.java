import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //Snake
    Tile snakeHead;

    //food
    Tile food;
    Random random;

    //logic
    Timer gameLoop;
    int velocityX;
    int velocityY;

    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5);

        food = new Tile(10, 10);
        random = new Random();
        placeFood();

        velocityX = 0;
        velocityY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //Food
        g.setColor(Color.red);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize);

        //snake
        g.setColor(Color.green);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
    }

    public void placeFood() {
        food.x = random.nextInt(boardWidth/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
    }

    public void move() {
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            velocityX = 0;
            velocityY = -1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            velocityX = 0;
            velocityY = 1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            velocityX = -1;
            velocityY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
