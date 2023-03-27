package core.pojo.Pieces;


import core.pojo.Piece;
import core.pojo.Position;
import manage.Pieces;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
//todo  temp code , needs cleanup and better
public class Knight extends Piece {

    public Knight(Position position, Color color) {
        super(position, color);
    }

    @Override
    public List<Position> getValidMoves() {
        List<Position> validMoves = new ArrayList<>();
        List<Position> possibleMoves = List.of(
                new Position(position.x() - 2, position.y()  - 1),
                new Position( position.x() - 2,  position.y()+ 1),
                new Position( position.x() - 1,  position.y()- 2),
                new Position( position.x() - 1,  position.y()+ 2),
                new Position( position.x() + 1, position.y() - 2),
                new Position( position.x() + 1,  position.y()+ 2),
                new Position( position.x() + 2,  position.y()- 1),
                new Position( position.x() + 2, position.y() + 1)
        );

        for (Position move : possibleMoves) {
            if (move.x() >= 0 && move.x() < 8 && move.y() >= 0 && move.y() < 8) {
                validMoves.add(move);
            }
        }

        return validMoves;
    }



    @Override
    protected void setPiece() {
        piece=Pieces.KNIGHT_BLACK;
        if(color==Color.white||color == Color.WHITE){
            this.piece=Pieces.KNIGHT;
        }

    }
}