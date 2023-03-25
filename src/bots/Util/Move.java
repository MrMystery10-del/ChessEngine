package bots.Util;

public class Move {
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    private byte capturedPiece;

    public Move(int fromRow, int fromCol, int toRow, int toCol, byte capturedPiece) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.capturedPiece = capturedPiece;
    }

    public int getFromRow() {
        return fromRow;
    }

    public int getFromCol() {
        return fromCol;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToCol() {
        return toCol;
    }

    public byte getCapturedPiece() {
        return capturedPiece;
    }

}
