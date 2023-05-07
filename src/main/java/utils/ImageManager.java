package utils;

import core.pojo.Board;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This class manages images of chess pieces
 */
public class ImageManager {


    // Map that associates each chess piece with its image representation

    private final static Map<Bots, Image> botPictures = new HashMap<>();
    private static final Logger logger = Logger.getLogger(Board.class.getName());
    private static Image default_profile_picture;

    private final static Map<Pieces, Image> pieceImages = new HashMap<>();

    // load items into memory without using object or constructor
    static {
        logger.info("loading image list provided in ImageManager");

        // Load the image for each chess piece and put it in the map with the corresponding key
        pieceImages.put(Pieces.PAWN, loadImage("/images/pieces/pawn.png"));
        pieceImages.put(Pieces.BISHOP, loadImage("/images/pieces/bishop.png"));
        pieceImages.put(Pieces.KNIGHT, loadImage("/images/pieces/knight.png"));
        pieceImages.put(Pieces.QUEEN, loadImage("/images/pieces/queen.png"));
        pieceImages.put(Pieces.KING, loadImage("/images/pieces/king.png"));
        pieceImages.put(Pieces.PAWN_BLACK, loadImage("/images/pieces/pawn_BLACK.png"));
        pieceImages.put(Pieces.BISHOP_BLACK, loadImage("/images/pieces/bishop_BLACK.png"));
        pieceImages.put(Pieces.KNIGHT_BLACK, loadImage("/images/pieces/knight_BLACK.png"));
        pieceImages.put(Pieces.ROOK_BLACK, loadImage("/images/pieces/rook_BLACK.png"));
        pieceImages.put(Pieces.QUEEN_BLACK, loadImage("/images/pieces/queen_BLACK.png"));
        pieceImages.put(Pieces.KING_BLACK, loadImage("/images/pieces/king_BLACK.png"));
        pieceImages.put(Pieces.ROOK, loadImage("/images/pieces/rook.png"));

        default_profile_picture = loadImage("/images/menu/default_profile_picture.png");

        //bot pictures
        botPictures.put(Bots.BOB, loadImage("/images/bots/bob_picture.png"));
        //TODO -> PICTURES
        botPictures.put(Bots.AVA, loadImage("/images/bots/bob_picture.png"));
        botPictures.put(Bots.MARKUS, loadImage("/images/bots/bob_picture.png"));
        botPictures.put(Bots.JAMES, loadImage("/images/bots/bob_picture.png"));

    }

    /**
     * Method to get the image for a specific chess piece</br>
     *
     * @param path location of the file
     * @return javaFx Image
     */
    // Method to load a single image given its file path
    private static Image loadImage(String path) {
        return new Image(String.valueOf(ImageManager.class.getResource(path)));
    }


    /**
     * Method to get the image for a specific chess piece
     *
     * @return returns the image of the given chess piece
     */
    public static Image getPiece(Pieces piece) {
        return pieceImages.get(piece);
    }

    //todo
    public static Image getPieceFX(Pieces piece) {
        return pieceImages.get(piece);
    }


    /**
     * Method to get the picture for a specific bot
     *
     * @return returns the picture of the given bot
     */
    public static Image getBotPicture(Bots bot) {
        return botPictures.get(bot);
    }

    /**
     * @return the default profile picture for the profile label
     */
    public static Image getDefaultPP() {
        return default_profile_picture;
    }


}
