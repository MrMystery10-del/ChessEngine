package core.pojo.pieces;

import core.pojo.ChessPiece;
import core.pojo.Position;
import core.pojo.interfaces.Piece;
import javafx.scene.image.Image;
import utils.ImageManager;
import utils.Pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Bishop extends ChessPiece implements Piece {


    public Bishop(Position position, Color color) {
        super(position, Pieces.BISHOP);

        if (color == Color.BLACK) {
            pieceType = Pieces.BISHOP_BLACK;
        }
    }

    @Override
    public Image getImage() {

        if (color == Color.BLACK) {
            return ImageManager.getPiece(Pieces.BISHOP_BLACK);
        }
        return ImageManager.getPiece(Pieces.BISHOP);
    }

    @Override
    public List<Position> getValidMoves() {

        java.util.List<Position> possibleMoves = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            possibleMoves.add(new Position((position.x() + i), (position.y()) + i));
            possibleMoves.add(new Position((position.x() + i), (position.y()) - i));
            possibleMoves.add(new Position((position.x() - i), (position.y()) + i));
            possibleMoves.add(new Position((position.x() - i), (position.y()) - i));
        }

        processMoves(possibleMoves);
        return validMoves;
    }
}
