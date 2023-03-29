package core.pojo;

import manage.Pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    protected Color color;
    protected Pieces piece;
    protected Position position;
    protected List<Position> validMoves;

    public Piece(Position position, Color color) {
        this.position = position;
        validMoves = new ArrayList<>();
        if (color == Color.BLACK || color == Color.black || color == Color.WHITE || color == Color.white)
            this.color = color;
    }

    public abstract java.util.List<Position> getValidMoves();

    /**
     * Sets which Pieces enum to return
     */
    protected abstract void setPiece();

    public Pieces getPiece() {
        return piece;
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
}