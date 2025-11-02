import java.util.List;
import java.util.ArrayList;

public class Ship
{
    private final String name;
    private final int length;
    private int hitsTaken;
    private List<String> occupiedCoordinates;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.hitsTaken = 0;
        this.occupiedCoordinates = new ArrayList<>();
    }

    public void addCoordinate(String coord) {
        this.occupiedCoordinates.add(coord);
    }

    public void recordHit() {
        this.hitsTaken++;
    }

    public boolean isSunk() {
        return this.hitsTaken >= this.length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public List<String> getOccupiedCoordinates() {
        return occupiedCoordinates;
    }

    public boolean occupies(int r, int c) {
        return occupiedCoordinates.contains(r + "," + c);
    }
}
