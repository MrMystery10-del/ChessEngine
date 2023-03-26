package bots.nooby;

import bots.Bot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Nooby extends Bot {
    private static final byte EMPTY = 0;
    public record Move(int fromRow, int fromCol, int toRow, int toCol, byte capturedPiece) {}

    public static byte[][] playNewMove(byte[][] board, boolean isWhiteTurn) {
        // Initialize a list of possible moves
        List<Move> possibleMoves = new ArrayList<>();

        // Loop through all the pieces on the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                byte piece = board[i][j];
                if ((piece > 0 && isWhiteTurn) || (piece < 0 && !isWhiteTurn)) {
                    // This is one of our pieces, so check its possible moves
                    List<Move> moves = getPossibleMoves(board, i, j);
                    possibleMoves.addAll(moves);
                }
            }
        }

        // If there are no possible moves, we've either checkmated or stalemated the opponent
        if (possibleMoves.isEmpty()) {
            return board;
        }


        // Sort the list of possible moves in descending order of the value of the captured piece (if any)
        Collections.sort(possibleMoves, (m1, m2) -> Integer.compare(m2.capturedPiece(), Math.abs(m1.capturedPiece())));

        // Select the move with the highest value capture (if any), or else just the first move in the list
        Move selectedMove = possibleMoves.get(0);

        // Apply the selected move to the board and return the resulting state
        byte[][] newBoard = applyMove(board, selectedMove);
        return newBoard;
    }

    // Returns a list of all possible moves for the piece at the specified position on the board
    private static List<Move> getPossibleMoves(byte[][] board, int row, int col) {
        List<Move> moves = new ArrayList<>();
        byte piece = board[row][col];
        int sign = (piece > 0) ? 1 : -1;

        // Check all possible moves for the piece, based on its type
        switch (Math.abs(piece)) {
            case 1: // Pawn
                int direction = (sign > 0) ? -1 : 1;
                // Check if pawn can move one square forward
                if (isValidPosition(row + direction, col) && board[row + direction][col] == 0) {
                    // Pawn can move one square forward
                    if (isValidPosition(row + 2 * direction, col) && ((sign > 0 && row == 6) || (sign < 0 && row == 1)) && board[row + 2 * direction][col] == 0) {
                        // Pawn can move two squares forward
                        moves.add(new Move(row, col, row + 2 * direction, col, (byte) 0));
                    }
                    moves.add(new Move(row, col, row + direction, col, (byte) 0));
                }
                // Check if pawn can capture diagonally
                if (isValidPosition(row + direction, col + 1) && board[row + direction][col + 1] * sign < 0) {
                    // Pawn can capture diagonally to the right
                    moves.add(new Move(row, col, row + direction, col + 1, board[row + direction][col + 1]));
                }
                if (isValidPosition(row + direction, col - 1) && board[row + direction][col - 1] * sign < 0) {
                    // Pawn can capture diagonally to the left
                    moves.add(new Move(row, col, row + direction, col - 1, board[row + direction][col - 1]));
                }
                break;
            case 2: // Knight
                int[][] knightMoves = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
                for (int[] move : knightMoves) {
                    int newRow = row + move[0];
                    int newCol = col + move[1];
                    if (isValidPosition(newRow, newCol)) {
                        byte capturedPiece = board[newRow][newCol];
                        if (capturedPiece * sign <= 0) {
                            // Either an opponent's piece or an empty square
                            moves.add(new Move(row, col, newRow, newCol, capturedPiece));
                        }
                    }
                }
                break;
            case 3: // Bishop
                // Check all diagonal directions
                int[][] bishopDirections = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
                for (int[] dir : bishopDirections) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    while (isValidPosition(newRow, newCol)) {
                        byte capturedPiece = board[newRow][newCol];
                        if (capturedPiece == 0) {
                            // Empty square, add move and continue in this direction
                            moves.add(new Move(row, col, newRow, newCol, (byte) 0));
                            newRow += dir[0];
                            newCol += dir[1];
                        } else if (capturedPiece * sign < 0) {
                            // Opponent's piece, add move and stop in this direction
                            moves.add(new Move(row, col, newRow, newCol, capturedPiece));
                            break;
                        } else {
                            // Our own piece, stop in this direction
                            break;
                        }
                    }
                }
                break;
            case 4: // Rook
                // Check all horizontal and vertical directions
                int[][] rookDirections = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                for (int[] dir : rookDirections) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    while (isValidPosition(newRow, newCol)) {
                        byte capturedPiece = board[newRow][newCol];
                        if (capturedPiece == 0) {
                            // Empty square, add move and continue in this direction
                            moves.add(new Move(row, col, newRow, newCol, (byte) 0));
                            newRow += dir[0];
                            newCol += dir[1];
                        } else if (capturedPiece * sign < 0) {
                            // Opponent's piece, add move and stop in this direction
                            moves.add(new Move(row, col, newRow, newCol, capturedPiece));
                            break;
                        } else {
                            // Our own piece, stop in this direction
                            break;
                        }
                    }
                }
                break;
            case 5: // Queen
                // Check all diagonal, horizontal, and vertical directions
                int[][] queenDirections = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
                for (int[] dir : queenDirections) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    while (isValidPosition(newRow, newCol)) {
                        byte capturedPiece = board[newRow][newCol];
                        if (capturedPiece == 0) {
                            // Empty square, add move and continue in this direction
                            moves.add(new Move(row, col, newRow, newCol, (byte) 0));
                            newRow += dir[0];
                            newCol += dir[1];
                        } else if (capturedPiece * sign < 0) {
                            // Opponent's piece, add move and stop in this direction
                            moves.add(new Move(row, col, newRow, newCol, capturedPiece));
                            break;
                        } else {
                            // Our own piece, stop in this direction
                            break;
                        }
                    }
                }
                break;
            case 6: // King
                // Check all adjacent squares
                int[][] kingMoves = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
                for (int[] move : kingMoves) {
                    int newRow = row + move[0];
                    int newCol = col + move[1];
                    if (isValidPosition(newRow, newCol)) {
                        byte capturedPiece = board[newRow][newCol];
                        if (capturedPiece * sign <= 0) {
                            // Either an opponent's piece or an empty square
                            moves.add(new Move(row, col, newRow, newCol, capturedPiece));
                        }
                    }
                }
                break;
        }
        return moves;
    }

    // Returns true if the specified position is a valid position on the board
    private static boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    // Applies the specified move to the specified board and returns the resulting state
    private static byte[][] applyMove(byte[][] board, Move move) {
        byte[][] newBoard = deepCopy(board);
        int startRow = move.fromRow();
        int startCol = move.fromCol();
        int endRow = move.toRow();
        int endCol = move.toCol();


        newBoard[endRow][endCol] = newBoard[startRow][startCol];
        newBoard[startRow][startCol] = EMPTY;
        return newBoard;
    }

    // Returns a deep copy of the specified board
    private static byte[][] deepCopy(byte[][] board) {
        byte[][] newBoard = new byte[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    // Represents a move on the chess board


//    private static class Move {
//        private int fromRow;
//        private int fromCol;
//        private int toRow;
//        private int toCol;
//        private byte capturedPiece;
//
//        public Move(int fromRow, int fromCol, int toRow, int toCol, byte capturedPiece) {
//            this.fromRow = fromRow;
//            this.fromCol = fromCol;
//            this.toRow = toRow;
//            this.toCol = toCol;
//            this.capturedPiece = capturedPiece;
//        }
//
//        public int getFromRow() {
//            return fromRow;
//        }
//
//        public int getFromCol() {
//            return fromCol;
//        }
//
//        public int getToRow() {
//            return toRow;
//        }
//
//        public int getToCol() {
//            return toCol;
//        }
//
//        public byte getCapturedPiece() {
//            return capturedPiece;
//        }

}