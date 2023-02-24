import java.util.LinkedList;

public class Snake {
    private int length;
    private boolean alive;
    private LinkedList<Coordinate> cells;

    public Snake() {
        this.length = 3;
        this.alive = true;
        cells = new LinkedList<>();
        cells.add(new Coordinate(3, 8));
        cells.add(new Coordinate(4, 8));
        cells.add(new Coordinate(5, 8));
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public LinkedList<Coordinate> getCells() {
        return cells;
    }

    public void setCells(LinkedList<Coordinate> cells) {
        this.cells = cells;
    }
}
