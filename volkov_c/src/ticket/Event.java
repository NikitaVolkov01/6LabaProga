package ticket;

import javax.xml.bind.ValidationException;
import java.io.Serializable;

public class Event implements Serializable {
    private String name;
    private float number;

    public Event() {}
    public void setName(String name) throws ValidationException {
        if (name == null) {
            throw new ValidationException("Имя не может быть null.");
        }
        if (name.equals("")) {
            throw new ValidationException("Имя не может быть пустой строкой.");
        }
        this.name = name;
    }
    public void setNumber(float num) throws ValidationException {
        if (!(num > 0)){
            throw new ValidationException("Количество билетов не может быть меньше нуля.");
        }
        this.number = num;
    }
}