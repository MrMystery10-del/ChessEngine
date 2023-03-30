package core.pojo.Pieces;

import core.pojo.Piece;
import core.pojo.Position;
import manage.Pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Bishop extends Piece {

    public Bishop(Position position, Color color) {
        super(position, color);
        setPiece();
    }

    @Override
    public List<Position> getValidMoves() {

        List<Position> possibleMoves = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            possibleMoves.add(new Position((position.x() + i), (position.y()) + i));
            possibleMoves.add(new Position((position.x() + i), (position.y()) - i));
            possibleMoves.add(new Position((position.x() - i), (position.y()) + i));
            possibleMoves.add(new Position((position.x() - i), (position.y()) - i));
        }
        processMoves(possibleMoves);

        return validMoves;
    }


    @Override
    protected void setPiece() {
        piece = Pieces.BISHOP_BLACK;
        if (color == Color.white || color == Color.WHITE)
            this.piece = Pieces.BISHOP;
    }
}
