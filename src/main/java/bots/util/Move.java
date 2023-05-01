package bots.util;

public record Move(int fromRow, int fromCol, int toRow, int toCol, byte capturedPiece) {}
