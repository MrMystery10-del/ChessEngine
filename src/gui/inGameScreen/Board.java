package gui.inGameScreen;

import content.Screen;
import manage.ImageManager;
import manage.Pieces;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;


/**
 * This class displays the board, pieces and highlighting
 */
public class Board extends Screen {


    private Graphics2D g2d;
    private final BufferedImage boardImage;

    public record GridCoordinate(double x, double y) {
    }

    private Map<String, GridCoordinate> coordinates;

    public Map<String, GridCoordinate> getCoordinates() {
        return Collections.unmodifiableMap(coordinates);
    }

    public Board() {
        try {
            boardImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/board.png")));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2d = (Graphics2D) g;
        g2d.drawImage(boardImage, 0, 0, getWidth(), getHeight(), null);
        determineGridLocations();

        java.util.List<String> whitePawnStartup = Arrays.asList("a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2");
        whitePawnStartup.forEach(a -> {
            GridCoordinate coordinate = coordinates.get(a);
            g2d.drawImage(ImageManager.getPiece(Pieces.PAWN), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        });

        java.util.List<String> blackPawnStartup = Arrays.asList("a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7");
        blackPawnStartup.forEach(a -> {
            GridCoordinate coordinate = coordinates.get(a);
            g2d.drawImage(ImageManager.getPiece(Pieces.PAWN_BLACK), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        });

        // rook
        GridCoordinate coordinate = coordinates.get("a1");
        g2d.drawImage(ImageManager.getPiece(Pieces.ROOK), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        coordinate = coordinates.get("h1");
        g2d.drawImage(ImageManager.getPiece(Pieces.ROOK), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        coordinate = coordinates.get("a8");
        g2d.drawImage(ImageManager.getPiece(Pieces.ROOK_BLACK), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        coordinate = coordinates.get("h8");
        g2d.drawImage(ImageManager.getPiece(Pieces.ROOK_BLACK), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);

        // knight
        coordinate = coordinates.get("b1");
        g2d.drawImage(ImageManager.getPiece(Pieces.KNIGHT), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        coordinate = coordinates.get("g1");
        g2d.drawImage(ImageManager.getPiece(Pieces.KNIGHT), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        coordinate = coordinates.get("b8");
        g2d.drawImage(ImageManager.getPiece(Pieces.KNIGHT_BLACK), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        coordinate = coordinates.get("g8");
        g2d.drawImage(ImageManager.getPiece(Pieces.KNIGHT_BLACK), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);

        // bishop
        coordinate = coordinates.get("c1");
        g2d.drawImage(ImageManager.getPiece(Pieces.BISHOP), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        coordinate = coordinates.get("f1");
        g2d.drawImage(ImageManager.getPiece(Pieces.BISHOP), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        coordinate = coordinates.get("c8");
        g2d.drawImage(ImageManager.getPiece(Pieces.BISHOP_BLACK), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        coordinate = coordinates.get("f8");
        g2d.drawImage(ImageManager.getPiece(Pieces.BISHOP_BLACK), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);

        // queen
        coordinate = coordinates.get("d1");
        g2d.drawImage(ImageManager.getPiece(Pieces.QUEEN), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        coordinate = coordinates.get("d8");
        g2d.drawImage(ImageManager.getPiece(Pieces.QUEEN_BLACK), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);

        // king
        coordinate = coordinates.get("e1");
        g2d.drawImage(ImageManager.getPiece(Pieces.KING), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
        coordinate = coordinates.get("e8");
        g2d.drawImage(ImageManager.getPiece(Pieces.KING_BLACK), (int) coordinate.x, (int) coordinate.y - 40, 80, 80, null);
    }

    /**
     * Create the center location of each piece according to grid
     */
    public void determineGridLocations() {
        if (coordinates == null) {
            coordinates = new HashMap<>();
            int xSize = 81;
            int ySize = 77;
            double startX = 75;
            double startY = 81;
            int counter = 96; // 'a' -1

            for (int i = 8; i >= 1; i--)
                for (int j = 1; j <= 8; j++)
                    coordinates.put((char) (counter + i) + String.valueOf(j), new GridCoordinate(startX + (xSize * (i - 1)), (800 - startY) - (ySize * j)));
        }
    }
}