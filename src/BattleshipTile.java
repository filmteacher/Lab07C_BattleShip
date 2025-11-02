import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Matt Bennett
 * Refactored TicTacToeTile by @author Tom Wulf
 */
public class BattleshipTile extends JButton
{
    private int row;
    private int col;
    private String state;

    // AI Disclosure - 
    // I used Gemini to generate the three icons for the game tiles,
    // so that I could have original, unique images instead of
    // something downloaded off the Internet.
    private ImageIcon empty = new ImageIcon("src/empty.png");
    private ImageIcon hit = new ImageIcon("src/hit.png");
    private ImageIcon miss = new ImageIcon("src/miss.png");

    public BattleshipTile(int row, int col)
    {
        super();
        this.row = row;
        this.col = col;
        this.setEmpty();

        setPreferredSize(new Dimension(50, 50));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getState() { return state;}

    public void setMiss() {
        this.setIcon(miss);
        this.state = "miss";
    }

    public void setHit() {
        this.setIcon(hit);
        this.state = "hit";
    }

    public void setEmpty() {
        this.setIcon(empty);
        this.state = "empty";
    }
}