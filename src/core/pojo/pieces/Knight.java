package core.pojo.pieces;


import core.pojo.Piece;
import core.pojo.Position;
import manage.Pieces;

import java.awt.*;
import java.util.List;


public class Knight extends Piece {

    public Knight(Position position, Color color) {
        super(position, color);
        setPiece();
    }

    @Override
    public List<Position> getValidMoves() {
        List<Position> possibleMoves = List.of(
                new Position(position.x() - 2, position.y() - 1),
                new Position(position.x() - 2, position.y() + 1),
                new Position(position.x() - 1, position.y() - 2),
                new Position(position.x() - 1, position.y() + 2),
                new Position(position.x() + 1, position.y() - 2),
                new Position(position.x() + 1, position.y() + 2),
                new Position(position.x() + 2, position.y() - 1),
                new Position(position.x() + 2, position.y() + 1)
        );
        processMoves(possibleMoves);

        return validMoves;
    }


    @Override
    protected void setPiece() {
        piece = Pieces.KNIGHT_BLACK;
        if (color == Color.white || color == Color.WHITE)
            piece = Pieces.KNIGHT;
    }
}