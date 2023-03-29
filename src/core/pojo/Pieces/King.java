package core.pojo.Pieces;

import core.pojo.Piece;
import core.pojo.Position;
import manage.Pieces;

import java.awt.*;
import java.util.List;

@SuppressWarnings("unused")
public class King extends Piece {

    public King(Position position, Color color) {
        super(position, color);
    }

    @Override
    public List<Position> getValidMoves() {

        List<Position> possibleMoves = List.of(
                new Position(position.x() + 1, position.y()),
                new Position(position.x() - 1, position.y()),

                new Position(position.x(), position.y() + 1),
                new Position(position.x(), position.y() - 1),

                new Position(position.x() + 1, position.y() + 1),
                new Position(position.x() + 1, position.y() - 1),
                new Position(position.x() - 1, position.y() + 1),
                new Position(position.x() - 1, position.y() - 1)
        );
        processMoves(possibleMoves);

        return validMoves;
    }


    @Override
    protected void setPiece() {
        piece = Pieces.KING_BLACK;
        if (color == Color.white || color == Color.WHITE)
            piece = Pieces.KING;
    }
}