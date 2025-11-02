import javax.swing.*;
import java.awt.*;

public class BattleshipGame {

    public BattleshipFrame frame;
    public BattleshipBoard board;

    private Fleet fleet;

    public int stikes = 0;
    public int misses = 0;
    public int totalHits = 0;
    public int totalMisses = 0;

    public BattleshipGame(BattleshipBoard board, BattleshipFrame frame)
    {
        this.board = board;
        this.frame = frame;
        this.initializeGame();
    }

    public void makeMove(int row, int col)
    {
        for (Ship ship : fleet.getShips())
        {
            if (ship.occupies(row, col)) {
                board.setBoardHit(row, col);
                ship.recordHit();
                frame.hitMessage();

                if (ship.isSunk()) {
                    String sunkShip = ship.getName();
                    frame.sunkMessage(sunkShip);
                }
                return;
            } else {
                board.setBoardMiss(row, col);
                frame.missMessage();
            }
        }
    }

    public void initializeGame()
    {
        board.clearBoard();

        fleet = new Fleet();
        fleet.placeFleet(board);

        frame.askMessage();
    }
}

