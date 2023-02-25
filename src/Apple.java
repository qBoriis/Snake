import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Apple {
    private Coordinate coordinate;
    private BufferedImage applePic;
    public Apple( Coordinate coordinate) {
        this.coordinate = coordinate;
        try {
            applePic = ImageIO.read(new File("appletransparent.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void render(Graphics g){
        g.drawImage(applePic, coordinate.getX() * 30 + 16, coordinate.getY() * 30 + 95,30, 30, null);
    }
}
