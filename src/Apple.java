import java.awt.*;

public class Apple {
    private Coordinate coordinate;

    public Apple( Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void render(Graphics g){
        g.setColor(Color.red.darker());
        g.fillRect(coordinate.getX() * 30 + 16, coordinate.getY() * 30 + 95, 30, 30);
    }
}
