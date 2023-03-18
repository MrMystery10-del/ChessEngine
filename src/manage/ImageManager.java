package manage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageManager {

    private static BufferedImage pawn, bishop, knight, rook, queen, king;
    private static BufferedImage pawnB, bishopB, knightB, rookB, queenB, kingB;

    @SuppressWarnings("ConstantConditions")
    public static void loadImages() throws IOException {
        pawn = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/pawn.png"));
        bishop = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/bishop.png"));
        knight = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/knight.png"));
        rook = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/rook.png"));
        queen = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/queen.png"));
        king = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/king.png"));

        pawnB = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/pawn_BLACK.png"));
        bishopB = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/bishop_BLACK.png"));
        knightB = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/knight_BLACK.png"));
        rookB = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/rook_BLACK.png"));
        queenB = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/queen_BLACK.png"));
        kingB = ImageIO.read(ImageManager.class.getResourceAsStream("/images/pieces/king_BLACK.png"));
    }

    public static BufferedImage getPiece(Pieces piece) {
        return switch (piece) {
            case PAWN -> pawn;
            case BISHOP -> bishop;
            case KNIGHT -> knight;
            case ROOK -> rook;
            case QUEEN -> queen;
            case KING -> king;
            case PAWN_BLACK -> pawnB;
            case BISHOP_BLACK -> bishopB;
            case KNIGHT_BLACK -> knightB;
            case ROOK_BLACK -> rookB;
            case QUEEN_BLACK -> queenB;
            case KING_BLACK -> kingB;
        };
    }
}
