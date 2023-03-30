package core.pojo.Pieces;

import core.pojo.Piece;
import core.pojo.Position;
import manage.Pieces;

import java.awt.*;
import java.util.List;


public class Queen extends Piece {
    public Queen(Position position, Color color) {
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
        for (int i = 1; i < 8; i++) {
            if (position.x() - i >= 0 && position.y() - i >= 0)
                validMoves.add(new Position(position.x() - i, position.y() - i));
            if (position.x() - i >= 0 && position.y() + i < 8)
                validMoves.add(new Position(position.x() - i, position.y() + i));
            if (position.x() + i < 8 && position.y() - i >= 0)
                validMoves.add(new Position(position.x() + i, position.y() - i));
            if (position.x() + i < 8 && position.y() + i < 8)
                validMoves.add(new Position(position.x() + i, position.y() + i));
        }
        return validMoves;
    }

    @Override
    protected void setPiece() {
        piece = Pieces.QUEEN_BLACK;
        if (color == Color.white || color == Color.WHITE)
            piece = Pieces.QUEEN;
    }
}