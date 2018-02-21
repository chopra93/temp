package dto;

/**
 * @author chopra
 * 21/02/18
 */
public class Car extends Vehicle {

    public Car() {
        super();
    }

    public Car(String regNo, String color) {
        super(regNo, color);
    }

    @Override
    public String toString() {
        return "Car{}";
    }
}
