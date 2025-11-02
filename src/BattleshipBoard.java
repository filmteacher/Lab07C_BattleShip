import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BattleshipBoard {
    public static final int ROW = 10;
    public static final int COL = 10;

    public String[][] board = new String[ROW][COL];

    private BattleshipTile[][] tiles = new BattleshipTile[ROW][COL];

    private JPanel boardPanel;

    public BattleshipBoard(ActionListener tileListener) {
        boardPanel = new JPanel();
        boardPanel.setBackground(Color.LIGHT_GRAY);
        boardPanel.setOpaque(true);
        boardPanel.setLayout(new GridLayout(ROW, COL));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                tiles[row][col] = new BattleshipTile(row, col);
                tiles[row][col].addActionListener(tileListener);
                boardPanel.add(tiles[row][col]);
            }
        }
        clearBoard();
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public String getBoardValue(int row, int col) {
        return board[row][col];
    }

    public void setBoardValue(int row, int col, String value) {
        this.board[row][col] = value;
    }

    public void setBoardHit(int row, int col) {
        tiles[row][col].setHit();
    }

    public void setBoardMiss(int row, int col) {
        tiles[row][col].setMiss();
    }

    public void setBoardEmpty(int row, int col) {
        tiles[row][col].setEmpty();
    }

    public void clearBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                board[row][col] = "~";
                tiles[row][col].setEmpty();
            }
        }
    }
}