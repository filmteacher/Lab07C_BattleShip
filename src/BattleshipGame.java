import javax.swing.*;
import java.awt.*;

public class BattleshipGame {

    public BattleshipFrame frame;
    public BattleshipBoard board;

    private Fleet fleet;

    public int strikes;
    public int misses;
    public int totalHits;
    public int totalMisses;

    public BattleshipGame(BattleshipBoard board, BattleshipFrame frame)
    {
        this.board = board;
        this.frame = frame;
        this.initializeGame();
    }

    public void makeMove(int row, int col)
    {
        for (Ship ship : fleet.getShips()) {
            if (ship.occupies(row, col)) {
                board.setBoardHit(row, col);
                ship.recordHit();
                totalHits++;
                frame.hitMessage();

                if (ship.isSunk()) {
                    String sunkShip = ship.getName();
                    frame.sunkMessage(sunkShip);
                }
                frame.setTotalHitsFld(String.valueOf(totalHits));
                return;
            }
        }

        board.setBoardMiss(row, col);
        misses ++;
        totalMisses ++;
        frame.setMissesFld(String.valueOf(misses));
        frame.setTotalMissesFld(String.valueOf(totalMisses));

        if (misses < 5) {
            frame.missMessage();
        } else {
            frame.strikeMessage();
            strikes ++;
            misses = 0;
            frame.setStrikesFld(String.valueOf(strikes));
            frame.setMissesFld(String.valueOf(misses));

            if (strikes == 3) {
                JOptionPane.showMessageDialog(frame,
                        "Three strikes. You lose!",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE);
                board.clearBoard();
                playAgain();
            }
        }
    }

    public void initializeGame()
    {
        board.clearBoard();

        fleet = new Fleet();
        fleet.placeFleet(board);

        frame.askMessage();

        strikes = 0;
        misses = 0;
        totalHits = 0;
        totalMisses = 0;

        frame.setStrikesFld(String.valueOf(strikes));
        frame.setMissesFld(String.valueOf(misses));
        frame.setTotalHitsFld(String.valueOf(totalHits));
        frame.setTotalMissesFld(String.valueOf(totalMisses));
    }

    private void playAgain()
    {
        int response = JOptionPane.showConfirmDialog(frame,
                "Would you like to play again?",
                "New Game",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION)
        {
            initializeGame();
        } else {
            System.exit(0);
        }
    }
}

