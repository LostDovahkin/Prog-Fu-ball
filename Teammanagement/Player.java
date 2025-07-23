package Teammanagement;

public abstract class Player {

    private String name;
    private int id;
    private int speed;
    private double precision;
    private int energy;

    public Player(int id, String name, int speed, double precision, int energy) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.precision = precision;
        this.energy = energy;
    }
}
