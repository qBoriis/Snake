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

    public Board(int size) {
        this.size = size;
        this.arr = new int[size][size];
        snake = new Snake();
        random = new Random();
        apple = new Apple(new Coordinate(random.nextInt(14) + 1, random.nextInt(14) + 1));
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


        if (direction.equals(Direction.RIGHT) && !lastDirection.equals(Direction.LEFT)) {
            coordinate = new Coordinate(snake.getCells().getLast().getX() + 1, snake.getCells().getLast().getY());
        } else if (direction.equals(Direction.LEFT) && !lastDirection.equals(Direction.RIGHT)) {
            coordinate = new Coordinate(snake.getCells().getLast().getX() - 1, snake.getCells().getLast().getY());
        } else if (direction.equals(Direction.UP) && !lastDirection.equals(Direction.DOWN)) {
            coordinate = new Coordinate(snake.getCells().getLast().getX(), snake.getCells().getLast().getY() - 1);
        } else if (direction.equals(Direction.DOWN) && !lastDirection.equals(Direction.UP)) {
            coordinate = new Coordinate(snake.getCells().getLast().getX(), snake.getCells().getLast().getY() + 1);
        }

        snake.getCells().add(coordinate);

        if(!snake.getCells().getLast().equals(apple.getCoordinate())){
            snake.getCells().remove(0);
        }else{
            snake.setLength(snake.getLength()+1);
            apple = new Apple(new Coordinate(random.nextInt(14) + 1, random.nextInt(14) + 1));
        }

        for (int i = 0; i < snake.getCells().size()-1; i++) {
            if(snake.getCells().getLast().equals(snake.getCells().get(i))) {
                gameState = false;
            }
        }

        if(snake.getCells().getLast().getX() < 0 || snake.getCells().getLast().getX() > 14 ||
                snake.getCells().getLast().getY() < 0 || snake.getCells().getLast().getY() > 14){
            gameState = false;
        }


        lastDirection = direction;

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(gameState){
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
                g.setColor(Color.lightGray);
                g.drawRect(snake.getCells().get(i).getX() * 30 + 16, snake.getCells().get(i).getY() * 30 + 95, 30, 30);
            }

            apple.render(g);
        }else{
            g.setColor(Color.black);
            g.fillRect(0,0,500,600);
            g.setColor(Color.white);
            g.drawString("Sie haben verloren (Sie hurensohn (respectfully))", 200,300);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w':
                direction = Direction.UP;
                break;
            case 'a':
                direction = Direction.LEFT;
                break;
            case 's':
                direction = Direction.DOWN;
                break;
            case 'd':
                direction = Direction.RIGHT;
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
