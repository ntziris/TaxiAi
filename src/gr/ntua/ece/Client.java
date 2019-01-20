package gr.ntua.ece;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client extends Point {
    private double destX;
    private double destY;
    private Date time;
    private int persons;
    private String lang;
    private int luggage;
    private Node closestNode;
    private Node closestNodeToDest;
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat ("hh:mm");

    public Client(double x, double y, double destX, double destY,
                  Date time, int persons, String lang, int luggage) {
        super(x, y);
        this.destX = destX;
        this.destY = destY;
        this.time = time;
        this.persons = persons;
        this.lang = lang;
        this.luggage = luggage;
    }

    public Client(String position, double destX, double destY,
                  Date time, int persons, String lang, int luggage) {
        super(position);
        this.destX = destX;
        this.destY = destY;
        this.time = time;
        this.persons = persons;
        this.lang = lang;
        this.luggage = luggage;
    }

    public double getDestX() {
        return destX;
    }

    public void setDestX(double destX) {
        this.destX = destX;
    }

    public double getDestY() {
        return destY;
    }

    public void setDestY(double destY) {
        this.destY = destY;
    }

    public Date getTime() {
        return time;
    }

    public String timeString() {
        SimpleDateFormat f = new SimpleDateFormat("hhmm");
        return f.format(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getLuggage() {
        return luggage;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }

    public Node getClosestNode() {
        return closestNode;
    }

    public void setClosestNode(Node closestNode) {
        this.closestNode = closestNode;
    }

    public Node getClosestNodeToDest() {
        return closestNodeToDest;
    }

    public void setClosestNodeToDest(Node closestNodeToDest) {
        this.closestNodeToDest = closestNodeToDest;
    }

    @Override
    public void print() {
        System.out.println("Client : ");
        System.out.println("\tposition: (x, y) = " + this.getX() + ", " + this.getY() + ")");
        System.out.println("\tdestination: (x, y) = " + this.destX + ", " + this.destY + ")");
        System.out.println("\tpersons = " + this.persons);
        System.out.println("\tlanguage = " + this.lang);
        System.out.println("\tluggage = " + this.luggage);
        System.out.println("\ttime = " + TIME_FORMAT.format(this.time));
    }
}
