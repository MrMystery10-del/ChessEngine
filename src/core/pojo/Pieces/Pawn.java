package core.pojo.Pieces;

import core.pojo.Piece;
import core.pojo.Position;
import manage.Pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Pawn extends Piece {

    public Pawn(Position position, Color color) {
        super(position, color);
        setPiece();
    }

    @Override
    public List<Position> getValidMoves() {
        List<Position> possibleMoves = new ArrayList<>();

        validMoves.add(new Position(position.x() + (color == Color.WHITE ? -1 : 1), position.y()));
        validMoves.add(new Position(position.x() + (color == Color.WHITE ? -1 : 1), position.y() - 1));
        validMoves.add(new Position(position.x() + (color == Color.WHITE ? -1 : 1), position.y() + 1));

        // 2 steps + color direction
        if (position.x() == 6 || position.x() == 1)
            validMoves.add(new Position(position.x() + (color == Color.WHITE ? -2 : 2), position.y()));

        processMoves(possibleMoves);

        return validMoves;
    }


    @Override
    protected void setPiece() {
        piece = Pieces.PAWN_BLACK;
        if (color == Color.white || color == Color.WHITE)
            piece = Pieces.PAWN;
    }
}