package core.pojo;

import utils.Pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {

    protected Color color;
    protected Pieces pieceType;
    protected Position position;
    protected List<Position> validMoves;


    public ChessPiece(Position position, Pieces piece) {
        this.position = position;
        validMoves = new ArrayList<>();
        pieceType = piece;
    }


    /**
     * Eliminates the out-of-bounds pieces that have been calculated on location of the piece
     *
     * @param possibleMoves List of possible moves
     */
    protected void processMoves(List<Position> possibleMoves) {
        for (Position move : possibleMoves)
            if (move.x() >= 0 && move.x() < 8 && move.y() >= 0 && move.y() < 8)
                validMoves.add(move);
    }

    public abstract java.util.List<Position> getValidMoves();
}
