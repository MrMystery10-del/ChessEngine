package gui.inGameScreen;

import content.Label;
import content.Screen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;


/**
 * This class displays the board, pieces and highlighting
 */
public class BoardGui extends Screen {


    int gridSize = 8;

    //private Graphics2D g2d;
    //private final BufferedImage boardImage;


    JPanel boardPanel;
    JButton[][] squares = new JButton[gridSize][gridSize];

    public BoardGui(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
//        try {
//            boardImage = ImageIO.read(getClass().getResourceAsStream("/images/board.png"));
//        } catch (IOException exception) {
//            throw new RuntimeException(" background image not found " + exception);
//        }


        boardPanel = new JPanel();


        boardPanel.setLayout(new BorderLayout(10, 10));
        boardPanel.setBounds(50, 50, 1800, 1000);
        boardPanel.setVisible(true);
        //boardPanel.setOpaque(false);

        designBoard();

        add(boardPanel);

    }


    @Override
    protected void paintComponent(Graphics g) {
        //todo background image that's better then the provided one
        super.paintComponent(g);
        //g2d = (Graphics2D) g;
        //g2d.drawImage(boardImage, 0, 0, getWidth(), getHeight(), null);

    }


    /**
     * drawing of the chessboard
     */
    private void designBoard() {


        setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        chessBoard.setForeground(Color.BLUE);
        boardPanel.add(chessBoard);
        chessBoard.setVisible(true);

        //a-h with empty gap in front
        JPanel notationsTop = new JPanel(new BorderLayout(10, 0));
        JLabel spacing = new JLabel();
        spacing.setForeground(Color.BLUE);
        spacing.setPreferredSize(new Dimension(68, 20));

        notationsTop.add(spacing, BorderLayout.LINE_START);
        notationsTop.setVisible(true);

        JPanel top = new JPanel(new GridLayout(1, 0));
        top.setVisible(true);
        for (int i = 97; i < 105; i++) {
            Label test = new Label(String.valueOf((char) i));
            top.add(test, BorderLayout.CENTER);
        }
        notationsTop.add(top, BorderLayout.CENTER);

        //1-8
        JPanel counterLeft = new JPanel(new GridLayout(0, 1));


        for (int i = 1; i < 9; i++) {
            counterLeft.add(new Label("   " + i + "   "));
        }

        JPanel centralSection = new JPanel(new GridLayout(8, 8));

        boolean needsBlack = false;

        for (int j = 0; j < gridSize; j++) {

            for (int i = 0; i < gridSize; i++) {
                JButton button = new JButton();
                button.setBackground(
                        needsBlack ? Color.WHITE : (Color.BLACK));

                button.setSize(80, 80);
                centralSection.add(button);
                squares[i][j] = button;
                //flip color field
                needsBlack = !needsBlack;

            }
            //flip on new line
            needsBlack = !needsBlack;
        }


        boardPanel.add(notationsTop, BorderLayout.PAGE_START);
        boardPanel.add(counterLeft, BorderLayout.LINE_START);
        boardPanel.add(centralSection, BorderLayout.CENTER);


    }
}


