package core.pojo.pieces;


import core.pojo.Piece;
import core.pojo.Position;
import manage.Pieces;

import java.awt.*;
import java.util.List;


public class Rook extends Piece {

    public Rook(Position position, Color color) {
        super(position, color);
        setPiece();
    }

    @Override
    public List<Position> getValidMoves() {
        for (int i = 0; i < 8; i++) {
            if (i != position.x())
                validMoves.add(new Position(i, position.y()));
            if (i != position.y())
                validMoves.add(new Position(position.x(), i));
        }
        return validMoves;
    }

    @Override
    protected void setPiece() {
        piece = Pieces.ROOK_BLACK;
        if (color == Color.white || color == Color.WHITE)
            piece = Pieces.ROOK;
    }
}