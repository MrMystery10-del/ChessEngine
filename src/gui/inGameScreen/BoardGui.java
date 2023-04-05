package gui.inGameScreen;

import bots.util.Move;
import content.Label;
import content.Screen;
import core.pojo.Board;
import gui.components.Block_Button;
import gui.controllers.PieceController;
import manage.ImageManager;
import manage.Pieces;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;


/**
 * This class displays the board, pieces and highlighting
 */
public class BoardGui extends Screen {

    private final JPanel boardPanel = new JPanel();
    public Block_Button[][] squares = new Block_Button[8][8];

    private final Board board;

    public BoardGui(int x, int y, int width, int height, Board board) {
        this.board = board;

        setBounds(x, y, width, height);

        boardPanel.setLayout(new BorderLayout(10, 10));
        boardPanel.setBounds(50, 50, 1800, 1000);
        boardPanel.setVisible(true);

        designBoard();

        add(boardPanel);
    }

    private Point selected;
    private Move[] possiblePositions;

    public void setSelected(int row, int col, Move[] moves) {
        Point clicked = new Point(row, col);

        if (clicked.equals(selected)) {
            return;
        } else {
            if (selected != null)
                squares[selected.x][selected.y].setBackground(squares[selected.x][selected.y].getColor());
            if (possiblePositions != null)
                for (int x = 0; x < possiblePositions.length; x++) {
                    squares[possiblePositions[x].toCol()][possiblePositions[x].toRow()].setBackground(squares[possiblePositions[x].toCol()][possiblePositions[x].toRow()].getColor());
                    if (possiblePositions[x].toRow() == clicked.x && possiblePositions[x].toCol() == clicked.y) {
                        // TODO apply move and update gui
                    }
                }
            selected = clicked;
            possiblePositions = moves;
        }

        squares[selected.x][selected.y].setBackground(Color.RED);
        for (int x = 0; x < possiblePositions.length; x++)
            squares[possiblePositions[x].toCol()][possiblePositions[x].toRow()].setBackground(Color.BLUE);
    }

    /**
     * drawing of the chessboard
     */
    private void designBoard() {
        drawBoardBorder();
        drawBoardFields();
    }

    private void drawBoardBorder() {
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

        JPanel counterLeft = new JPanel(new GridLayout(0, 1));


        for (int i = 8; i >= 1; i--) {
            counterLeft.add(new Label("   " + i + "   "));
        }

        boardPanel.add(notationsTop, BorderLayout.PAGE_START);
        boardPanel.add(counterLeft, BorderLayout.LINE_START);
    }

    private void drawBoardFields() {
        JPanel centralSection = new JPanel(new GridLayout(8, 8));
        Pieces[][] pieces = convertToPiece(board.getGameBoard());

        boolean needsBlack = true;

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {
                Block_Button button = new Block_Button(j, i);

                if (ImageManager.getPiece(pieces[i][j]) != null)
                    button.setIcon(new ImageIcon(ImageManager.getPiece(pieces[i][j])));

                button.setColor(needsBlack ? Color.LIGHT_GRAY : Color.darkGray);
                button.setBackground(needsBlack ? Color.LIGHT_GRAY : Color.darkGray);
                button.addActionListener(new PieceController(button, board, this));
                button.setSize(80, 80);

                centralSection.add(button);

                squares[j][i] = button;

                needsBlack = !needsBlack;
            }
            needsBlack = !needsBlack;
        }
        boardPanel.add(centralSection, BorderLayout.CENTER);
    }

    private Pieces[][] convertToPiece(byte[][] board) {
        Pieces[][] convertedBoard = new Pieces[8][8];

        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                convertedBoard[x][y] = switch (board[x][y]) {
                    case 0 -> null;
                    case 1 -> Pieces.PAWN;
                    case 2 -> Pieces.KNIGHT;
                    case 3 -> Pieces.BISHOP;
                    case 4 -> Pieces.ROOK;
                    case 5 -> Pieces.QUEEN;
                    case 6 -> Pieces.KING;
                    case -1 -> Pieces.PAWN_BLACK;
                    case -2 -> Pieces.KNIGHT_BLACK;
                    case -3 -> Pieces.BISHOP_BLACK;
                    case -4 -> Pieces.ROOK_BLACK;
                    case -5 -> Pieces.QUEEN_BLACK;
                    case -6 -> Pieces.KING_BLACK;
                    default -> throw new IllegalStateException("Unexpected value: " + board[x][y]);
                };
        return convertedBoard;
    }

    public JButton[][] getSquares() {
        return squares;
    }
}


