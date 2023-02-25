import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Board extends JPanel implements KeyListener {
    private final int size;
    private final int[][] arr;
    private Snake snake;
    private Direction direction = Direction.RIGHT;
    private Coordinate coordinate;
    private Apple apple;
    private Random random;
    private boolean gameState = true;
    private Direction lastDirection = direction;
    private int score;
    private JButton restartButton;

    public Board(int size) {
        this.size = size;
        this.arr = new int[size][size];
        snake = new Snake();
        random = new Random();
        generateApple();
        setBackground(new Color(21, 38, 20));
    }

    public void paintBoard() {
        JFrame frame = new JFrame("Board");
        frame.setSize(500, 600);
        frame.add(this);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addKeyListener(this);
    }

    enum Direction {UP, DOWN, LEFT, RIGHT}

    public void moveSnake() {
        if(!gameState) return;

        if (direction.equals(Direction.RIGHT) && !lastDirection.equals(Direction.LEFT)) {
            coordinate = new Coordinate(snake.getCells().getLast().getX() + 1, snake.getCells().getLast().getY());
            lastDirection = direction;
        } else if (direction.equals(Direction.LEFT) && !lastDirection.equals(Direction.RIGHT)) {
            coordinate = new Coordinate(snake.getCells().getLast().getX() - 1, snake.getCells().getLast().getY());
            lastDirection = direction;
        } else if (direction.equals(Direction.UP) && !lastDirection.equals(Direction.DOWN)) {
            coordinate = new Coordinate(snake.getCells().getLast().getX(), snake.getCells().getLast().getY() - 1);
            lastDirection = direction;
        } else if (direction.equals(Direction.DOWN) && !lastDirection.equals(Direction.UP)) {
            coordinate = new Coordinate(snake.getCells().getLast().getX(), snake.getCells().getLast().getY() + 1);
            lastDirection = direction;
        }

        snake.getCells().add(coordinate);

        // apple
        if (!snake.getCells().getLast().equals(apple.getCoordinate())) {
            snake.getCells().remove(0);
        } else {
            score++;
            snake.setLength(snake.getLength() + 1);
            generateApple();
        }

        // collision with snake
        for (int i = 0; i < snake.getCells().size() - 1; i++) {
            if (snake.getCells().getLast().equals(snake.getCells().get(i))) {
                gameState = false;
            }
        }

        // collision with wall
        if (snake.getCells().getLast().getX() < 0 || snake.getCells().getLast().getX() > 14 ||
                snake.getCells().getLast().getY() < 0 || snake.getCells().getLast().getY() > 14) {
            gameState = false;
        }
    }


    private void generateApple() {
        boolean appleOnSnake = true;
        Coordinate newAppleCoord = null;
        while (appleOnSnake) {
            newAppleCoord = new Coordinate(random.nextInt(size), random.nextInt(size));
            appleOnSnake = false;
            for (Coordinate cell : snake.getCells()) {
                if (newAppleCoord.equals(cell)) {
                    appleOnSnake = true;
                    break;
                }
            }
        }
        apple = new Apple(newAppleCoord);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean b = true;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (b) {
                    g.setColor(new Color(42, 114, 33));
                } else {
                    g.setColor(new Color(17, 152, 34));
                }
                g.fillRect(i * 30 + 16, j * 30 + 95, 30, 30);
                b = !b;
            }
        }

        for (int i = 0; i < snake.getLength(); i++) {
            if (i == snake.getLength() - 1) {
                g.setColor(Color.blue.darker());
            } else {
                g.setColor(Color.blue);
            }
            g.fillRect(snake.getCells().get(i).getX() * 30 + 16, snake.getCells().get(i).getY() * 30 + 95, 30, 30);
        }

        apple.render(g);
        g.setColor(Color.white);
        g.drawString("Score: " + score, 20, 50);
        if (!gameState) {
            g.setColor(new Color(21, 38, 20));
            g.fillRect(150, 255, 240, 75);
            g.setColor(Color.white);
            g.drawString("Sie haben verloren! | Score: " + score, 180, 290);
            restart();
        }
    }

    public void restart(){
        gameState = true;
        score = 0;
        snake = new Snake();
        generateApple();
        direction = Direction.RIGHT;
        lastDirection = direction;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
                if (lastDirection != Direction.DOWN) {
                    direction = Direction.UP;
                }
                break;
            case 'a':
                if (lastDirection != Direction.RIGHT) {
                    direction = Direction.LEFT;
                }
                break;
            case 's':
                if (lastDirection != Direction.UP) {
                    direction = Direction.DOWN;
                }
                break;
            case 'd':
                if (lastDirection != Direction.LEFT) {
                    direction = Direction.RIGHT;
                }
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
