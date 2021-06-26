package CollectionElements;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private double x;
    private Integer y;

    public Coordinates(double x, int y){
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
