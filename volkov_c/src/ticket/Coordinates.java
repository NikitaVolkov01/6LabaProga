package ticket;

import javax.xml.bind.ValidationException;
import java.io.Serializable;

public class Coordinates implements Serializable {
    private Long x;
    private float y;
    public Coordinates(Long x, float y) throws ValidationException {
        setX(x);
        setY(y);
    }
    public Coordinates() {
    }
    public Long getX() {
        return x;
    }
    public void setX(Long x) throws ValidationException {
        this.x = x;
    }
    public Float getY() {
        return y;
    }
    public void setY(float y) throws ValidationException {
        if (y > 870) {
            throw new ValidationException("Значение y должно быть меньше или равно 870.");
        }
        this.y = y;
    }
}