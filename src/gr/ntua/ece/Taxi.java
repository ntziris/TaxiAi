package gr.ntua.ece;

import org.jetbrains.annotations.NotNull;

public class Taxi extends Point implements Comparable<Taxi> {
    private long id;
    private Boolean isAvailable;
    private int capacity;
    private String[] languages;
    private double rating;
    private Boolean longDistances;
    private String description;
    private Node closestNode;
    private Double min;
    private int counter;


    public Taxi(double x, double y, long id, Boolean isAvailable, int capacity, String[] languages,
                double rating, Boolean longDistances, String description) {
        super(x, y);
        this.id = id;
        this.isAvailable = isAvailable;
        this.capacity = capacity;
        this.languages = languages;
        this.rating = rating;
        this.longDistances = longDistances;
        this.description = description;
    }

    public Taxi(String position, long id, Boolean isAvailable, int capacity, String[] languages,
                double rating, Boolean longDistances, String description) {
        super(position);
        this.id = id;
        this.isAvailable = isAvailable;
        this.capacity = capacity;
        this.languages = languages;
        this.rating = rating;
        this.longDistances = longDistances;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Boolean getLongDistances() {
        return longDistances;
    }

    public void setLongDistances(Boolean longDistances) {
        this.longDistances = longDistances;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Node getClosestNode() {
        return closestNode;
    }

    public void setClosestNode(Node closestNode) {
        this.closestNode = closestNode;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Boolean speaks(String language) {
        for (String s : this.languages) {
            if (s.compareTo(language) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void print() {
        System.out.println("Taxi " + this.id + ": ");
        System.out.println("\tposition: (x, y) = " + this.getX() + ", " + this.getY() + ")");
        System.out.println("\tis available: " + this.isAvailable);
        System.out.println("\tmax capacity = " + this.capacity);
        System.out.println("\tlanguages = " + this.languages);
        System.out.println("\trating = " + this.rating);
        System.out.println("\tdescription = " + this.description);
    }

    @Override
    public int compareTo(@NotNull Taxi taxi) {
        if (this.rating > taxi.getRating()) {
            return -1;
        }

        if (this.rating < taxi.getRating()) {
            return 1;
        }

        return 0;
    }
}
