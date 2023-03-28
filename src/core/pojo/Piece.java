package core.pojo;

import manage.Pieces;


import java.awt.*;

public abstract class Piece {



    protected Color color;
    protected Pieces piece;
    protected Position position;


    public Piece( Position position,Color color) {
        this.position=position;
        if (color==Color.BLACK|| color==Color.black || color==Color.WHITE || color==Color.white ) {
            this.color = color;
        }


    }

    public abstract java.util.List<Position> getValidMoves();

    /**
     * Sets which Pieces enum to return
     */
    protected abstract void setPiece();

    public  Pieces getPiece(){
        return this.piece;
    };



}