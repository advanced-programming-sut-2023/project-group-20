package model.Game;

public class Troop {
    private String state;
    private String type;
    private Integer speed;
    private Integer power;
    private Integer hitpoint;

    public void setState(String state) {
        this.state = state;
    }

    public Troop(String type, Integer speed, Integer power, Integer hitpoint) {
        this.type = type;
        this.speed = speed;
        this.power = power;
        this.hitpoint = hitpoint;
    }
}
