import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleshipFrame extends JFrame implements ActionListener
{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel msgPnl;

    JTextField textFld;
    JLabel titleLbl;

    public BattleshipGame game;
    public BattleshipBoard board;

    public BattleshipFrame()
    {
        this.setTitle("Battleship");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = 500;
        int frameHeight = 700;
        this.setSize(frameWidth, frameHeight);

        int x = (screenSize.width - frameWidth) / 2;
        int y = (screenSize.height - frameHeight) / 2;
        this.setLocation(x, y);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        createTopPanel();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createMiddlePanel();
        mainPnl.add(msgPnl, BorderLayout.CENTER);

        board = new BattleshipBoard(this);
        game = new BattleshipGame(board, this);

        mainPnl.add(board.getBoardPanel(), BorderLayout.SOUTH);

        add(mainPnl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createTopPanel()
    {
        titlePnl = new JPanel();
        titlePnl.setBackground(Color.LIGHT_GRAY);
        titlePnl.setOpaque(true);
        titlePnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        titleLbl = new JLabel("Battleship", JLabel.CENTER);
        titleLbl.setFont(new Font("Verdana", Font.BOLD, 32));
        titleLbl.setForeground(Color.BLACK);
        titleLbl.setVerticalTextPosition(JLabel.TOP);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        titlePnl.add(titleLbl);
    }

    private void createMiddlePanel()
    {
        msgPnl = new JPanel();
        msgPnl.setBackground(Color.LIGHT_GRAY);
        msgPnl.setOpaque(true);
        msgPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        textFld = new JTextField(14);
        textFld.setFont(new Font("Verdana", Font.BOLD, 14));
        textFld.setForeground(Color.BLACK);
        textFld.setHorizontalAlignment(SwingConstants.CENTER);
        textFld.setEditable(false);
        msgPnl.add(textFld);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!(e.getSource() instanceof BattleshipTile))
        {
            return;
        }

        BattleshipTile clickedTile = (BattleshipTile) e.getSource();
        int row = clickedTile.getRow();
        int col = clickedTile.getCol();

        if (!game.isValidMove(row, col))
        {
            JOptionPane.showMessageDialog(this, "Invalid move! Cell has already been selected.",
                    "Invalid Move", JOptionPane.WARNING_MESSAGE);
            return;
        }

        game.makeMove(row, col);
    }

    public void askMessage() {
        textFld.setText("Choose a cell.");
    }

    public void hitMessage() {
        textFld.setText("It's a hit!");
    }

    public void missMessage() {
        textFld.setText("It's a miss.");
    }
}