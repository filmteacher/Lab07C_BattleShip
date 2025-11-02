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
    JPanel bottomPnl;
    JPanel statusPnl;
    JPanel buttonsPnl;

    JLabel titleLbl;

    JTextField msgFld;

    JTextField strikesFld;
    JLabel strikesLbl;
    JTextField missesFld;
    JLabel missesLbl;
    JTextField totalHitsFld;
    JLabel totalHitsLbl;
    JTextField totalMissesFld;
    JLabel totalMissesLbl;

    JButton replayBtn;
    JButton quitBtn;

    public BattleshipGame game;
    public BattleshipBoard board;

    public BattleshipFrame()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = 540;
        int frameHeight = 840;
        this.setSize(frameWidth, frameHeight);

        int x = (screenSize.width - frameWidth) / 2;
        int y = (screenSize.height - frameHeight) / 2;
        this.setLocation(x, y);

        createMainPnl();
        createTitlePnl();
        createMsgPnl();
        createBottomPnl();

        mainPnl.add(titlePnl);
        mainPnl.add(msgPnl);

        board = new BattleshipBoard(this);
        game = new BattleshipGame(board, this);

        mainPnl.add(board.getBoardPanel());
        mainPnl.add(bottomPnl);

        add(mainPnl);

        setTitle("Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createMainPnl()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new FlowLayout());
        mainPnl.setBackground(Color.LIGHT_GRAY);
        mainPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

   private void createTitlePnl()
    {
        titlePnl = new JPanel();
        titlePnl.setLayout(new GridLayout(1,1));
        titlePnl.setBackground(Color.LIGHT_GRAY);
        titlePnl.setOpaque(true);
        titlePnl.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));

        titleLbl = new JLabel("Battleship", JLabel.CENTER);
        titleLbl.setFont(new Font("Verdana", Font.BOLD, 32));
        titleLbl.setForeground(Color.BLACK);
        titleLbl.setVerticalTextPosition(JLabel.TOP);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        titlePnl.add(titleLbl);
    }

    private void createMsgPnl()
    {
        msgPnl = new JPanel();
        msgPnl.setLayout(new GridLayout(1,1));
        msgPnl.setBackground(Color.LIGHT_GRAY);
        msgPnl.setOpaque(true);
        msgPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        msgFld = new JTextField(25);
        msgFld.setFont(new Font("Verdana", Font.BOLD, 14));
        msgFld.setForeground(Color.BLACK);
        msgFld.setHorizontalAlignment(SwingConstants.CENTER);
        msgFld.setEditable(false);
        msgPnl.add(msgFld);
        askMessage();
    }

    private void createBottomPnl()
    {
        bottomPnl = new JPanel();
        bottomPnl.setLayout(new GridLayout(1,2));
        bottomPnl.setBackground(Color.LIGHT_GRAY);
        bottomPnl.setOpaque(true);
        bottomPnl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        statusPnl = new JPanel();
        statusPnl.setLayout(new GridLayout(4,2));
        statusPnl.setBackground(Color.LIGHT_GRAY);
        statusPnl.setFont(new Font("Verdana", Font.BOLD, 14));
        statusPnl.setForeground(Color.BLACK);
        statusPnl.setOpaque(true);
        statusPnl.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        strikesLbl = new JLabel("Strikes:");
        statusPnl.add(strikesLbl);

        strikesFld = new JTextField(3);
        strikesFld.setEditable(false);
        statusPnl.add(strikesFld);
        strikesFld.setText("0");

        missesLbl = new JLabel("Misses:");
        statusPnl.add(missesLbl);

        missesFld = new JTextField(3);
        missesFld.setEditable(false);
        statusPnl.add(missesFld);
        missesFld.setText("0");

        totalHitsLbl = new JLabel("Total Hits:");
        statusPnl.add(totalHitsLbl);

        totalHitsFld = new JTextField(3);
        totalHitsFld.setEditable(false);
        statusPnl.add(totalHitsFld);
        totalHitsFld.setText("0");

        totalMissesLbl = new JLabel("Total Misses:");
        statusPnl.add(totalMissesLbl);

        totalMissesFld = new JTextField(3);
        totalMissesFld.setEditable(false);
        statusPnl.add(totalMissesFld);
        totalMissesFld.setText("0");


        buttonsPnl = new JPanel();
        buttonsPnl.setLayout(new GridLayout(2,1));
        buttonsPnl.setBackground(Color.LIGHT_GRAY);
        buttonsPnl.setFont(new Font("Verdana", Font.BOLD, 14));
        buttonsPnl.setForeground(Color.BLACK);
        buttonsPnl.setOpaque(true);
        buttonsPnl.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        quitBtn = new JButton("QUIT");
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            System.exit(0);
        });

        replayBtn = new JButton("RESTART");
        replayBtn.addActionListener((ActionEvent ae) ->
        {
            game.initializeGame();
        });

        buttonsPnl.add(quitBtn);
        buttonsPnl.add(replayBtn);

        bottomPnl.add(statusPnl);
        bottomPnl.add(buttonsPnl);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!(e.getSource() instanceof BattleshipTile))
        {
            return;
        } else {
            BattleshipTile clickedTile = (BattleshipTile) e.getSource();
            int row = clickedTile.getRow();
            int col = clickedTile.getCol();
            String state = clickedTile.getState();

            if (state.equals("hit") || state.equals("miss"))
            {
                usedMessage();
            } else {
                game.makeMove(row, col);
            }
        }
    }

    public void askMessage() {
        msgFld.setText("Choose a cell.");
    }

    public void usedMessage() {
        msgFld.setText("You already clicked that cell. Choose again.");
    }

    public void hitMessage() {
        msgFld.setText("It's a hit! Choose again.");
    }

    public void missMessage() {
        msgFld.setText("It's a miss. Choose again.");
    }

    public void sunkMessage(String ship) {
        msgFld.setText("You sank the opponent's " + ship + "!");
    }

    public void strikeMessage() {
        msgFld.setText("Five misses. That's a strike!");
    }

    public void setStrikesFld(String value) {
        strikesFld.setText(value);
    }

    public void setMissesFld(String value) {
        missesFld.setText(value);
    }

    public void setTotalHitsFld(String value) {
        totalHitsFld.setText(value);
    }

    public void setTotalMissesFld(String value) {
        totalMissesFld.setText(value);
    }
}