import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Fleet {
    private final List<Ship> ships;
    private final Map<String, Integer> SHIP_MAP = Map.of(
            "Carrier", 5,
            "Battleship", 4,
            "Cruiser", 3,
            "Submarine", 3,
            "Destroyer", 2
    );

    public Fleet() {
        this.ships = new ArrayList<>();
    }

    public boolean fleetSunk() {
        return ships.isEmpty();
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void placeFleet(BattleshipBoard board)
    {
        Random random = new Random();

        for (Map.Entry<String, Integer> entry : SHIP_MAP.entrySet()) {
            String shipName = entry.getKey();
            int length = entry.getValue();

            Ship currentShip = new Ship(shipName, length);
            boolean placed = false;

            while (!placed) {

                String orientation = (random.nextInt(2) == 0) ? "H" : "V";

                int rowBound = (orientation.equals("V")) ? 10 - length + 1 : 10;
                int colBound = (orientation.equals("H")) ? 10 - length + 1 : 10;

                int startRow = random.nextInt(rowBound);
                int startCol = random.nextInt(colBound);

                if (attemptPlacement(board, currentShip, startRow, startCol, orientation))
                {
                    this.ships.add(currentShip);
                    placed = true;
                }
            }
        }
    }

    private boolean attemptPlacement(BattleshipBoard board, Ship ship, int startRow, int startCol, String orientation)
    {
        int length = ship.getLength();
        List<String> plannedCoords = new ArrayList<>();

        if (orientation.equals("H"))
        {
            for (int c = startCol; c < startCol + length; c++)
            {
                if (!board.getBoardValue(startRow, c).trim().equals("~"))
                {
                    return false;
                }
                plannedCoords.add(startRow + "," + c);
            }
        } else {
            for (int r = startRow; r < startRow + length; r++)
            {
                if (!board.getBoardValue(r, startCol).trim().equals("~"))
                {
                    return false;
                }
                plannedCoords.add(r + "," + startCol);
            }
        }

        String marker = ship.getName().substring(0, 1);
        for (String coord : plannedCoords) {
            String[] parts = coord.split(",");
            int r = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[1]);

            board.setBoardValue(r, c, marker);
            ship.addCoordinate(coord);
        }
        return true;
    }
}
