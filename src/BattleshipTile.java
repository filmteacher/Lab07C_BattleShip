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

    // I used AI to generate the icons for the game tiles so that I could have original, unique images.
    // --- Images: empty.png, hit.png, and miss.png ---
    // Source: AI generated via Google Gemini Pro
    // Date Generated: 1 November 2025
    // License: Royalty-free for commercial and personal use.
    // ---------------------------------

    private ImageIcon empty = new ImageIcon("src/empty.png");
    private ImageIcon hit = new ImageIcon("src/hit.png");
    private ImageIcon miss = new ImageIcon("src/miss.png");

    public BattleshipTile(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        this.setIcon(empty);

        setPreferredSize(new Dimension(50, 50));
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setMiss() {
        this.setIcon(miss);
    }

    public void setHit() {
        this.setIcon(hit);
    }

    public void setEmpty() {
        this.setIcon(empty);
    }
}