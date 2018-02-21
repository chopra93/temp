package dto;

/**
 * @author chopra
 * 21/02/18
 */
public class Vehicle {
    private String regNo;
    private String color;

    public Vehicle() {
    }

    public Vehicle(String regNo, String color) {
        this.regNo = regNo;
        this.color = color;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "regNo='" + regNo + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
