package bots.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
    private static boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public static List<Move> commonLoop(int piece, int row, int col, byte[][] board, int sign) {
        List<Move> moves = new ArrayList<>();
        int[][] Moves = null;
        switch (piece) {
            case 1 -> Moves = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
            case 2 -> Moves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            case 3 -> Moves = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        }
        int newrow = row;
        int newcol = col;

        for (int i = 0; i < Moves.length; i++) {
            while (isValidPosition(newrow + Moves[i][1] * sign, newcol + Moves[i][0] * sign)) {
                newrow += Moves[i][1] * sign;
                newcol += Moves[i][0] * sign;
                if (board[newcol][newrow] == 0) {
                    moves.add(new Move(row, col, newrow, newcol, (byte) 0));
                } else if (board[newcol][newrow] < 0 && sign > 0||board[newcol][newrow] > 0 && sign < 0) {
                    moves.add(new Move(row, col, newrow, newcol, (byte) 1));
                    break;
                } else break;
            }
            newcol = col;
            newrow = row;
        }
        return moves;
    }

    public static byte[][] deepCopy(byte[][] board) {
        byte[][] newBoard = new byte[8][8];
        for (int i = 0; i < 8; i++)
            System.arraycopy(board[i], 0, newBoard[i], 0, 8);
        return newBoard;
    }

    public static byte[][] applyMove(byte[][] board, Move move) {
        byte[][] newBoard = Util.deepCopy(board);
        int startRow = move.fromRow();
        int startCol = move.fromCol();
        int endRow = move.toRow();
        int endCol = move.toCol();
        newBoard[endRow][endCol] = newBoard[startRow][startCol];
        newBoard[startRow][startCol] = 0;
        return newBoard;
    }

    public static List<Move> getPossibleMoves(byte[][] board, int col, int row) {
        List<Move> moves = new ArrayList<>();
        byte piece = board[col][row];
        int sign = (piece > 0) ? 1 : -1;
        System.out.println(piece);
        // Check all possible moves for the piece, based on its type
        switch (Math.abs(piece)) {
            case 1 -> { // Pawn
                int start_location_black = 1;
                int start_location_white = 6;
                if (isValidPosition(row, col + (-1 * sign)) && board[col + (-1 * sign)][row] == 0) {
                    moves.add(new Move(row, col, row, col + (-1 * sign), (byte) 0));
                }
                if (isValidPosition(row, col + (-2 * sign)) && board[col + (-2 * sign)][row] == 0 && board[col + (-1 * sign)][row] == 0) {
                    if (sign < 0 && col == start_location_black) {
                        moves.add(new Move(row, col, row, col + (-2 * sign), (byte) 0));
                    } else {
                        if (sign > 0 && col == start_location_white) {
                            moves.add(new Move(row, col, row, col + (-2 * sign), (byte) 0));
                        }
                    }
                }
                if (isValidPosition(row + (-1 * sign), col + (-1 * sign))) {
                    if (sign < 0 && board[col + (-1 * sign)][row + (-1 * sign)] > 0 || sign > 0 && board[col + (-1 * sign)][row + (-1 * sign)] < 0) {
                        moves.add(new Move(row, col, row + (-1 * sign), col + (-1 * sign), (byte) 1));
                    }
                }
                if (isValidPosition(row + (sign), col + (-1 * sign))) {
                    if (sign < 0 && board[col + (-1 * sign)][row + (sign)] > 0 || sign > 0 && board[col + (-1 * sign)][row + (sign)] < 0) {
                        moves.add(new Move(row, col, row + (sign), col + (-1 * sign), (byte) 1));
                    }
                }
            }
            case 2 -> { // Knight
                int[][] Moves = {{2, -1}, {2, 1}, {1, 2}, {1, -2}, {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}};
                for (int i = 0; i < Moves.length; i++) {
                    if (isValidPosition(row + (Moves[i][1] * sign), col + (Moves[i][0] * sign)) && board[col + (Moves[i][0] * sign)][row + (Moves[i][1] * sign)] == 0) {
                        moves.add(new Move(row, col, row + (Moves[i][1] * sign), col + (Moves[i][0] * sign), (byte) 0));
                    }
                    if (isValidPosition(row + (Moves[i][1] * sign), col + (Moves[i][0] * sign))) {
                        if (sign < 0 && board[col + (Moves[i][0] * sign)][row + (Moves[i][1] * sign)] > 0) {
                            moves.add(new Move(row, col, row + (Moves[i][1] * sign), col + (Moves[i][0] * sign), (byte) 1));
                        }
                        if (sign > 0 && board[col + (Moves[i][0] * sign)][row + (Moves[i][1] * sign)] < 0) {
                            moves.add(new Move(row, col, row + (Moves[i][1] * sign), col + (Moves[i][0] * sign), (byte) 1));
                        }
                    }

                }
            }
            case 3 -> { // Bishop
                moves = commonLoop(1, row, col, board, sign);
            }
            case 4 -> {
                moves = commonLoop(2, row, col, board, sign);
            }
            case 5 -> {
                moves = commonLoop(3, row, col, board, sign);
            }
            case 6 -> { // King
                // Check all adjacent squares
                int[][] kingMoves = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
                for (int i = 0; i < kingMoves.length; i++) {
                    if (isValidPosition(row + kingMoves[i][1] * sign, col + kingMoves[i][0] * sign) && board[col + kingMoves[i][0] * sign][row + kingMoves[i][1] * sign] == 0) {
                        moves.add(new Move(row, col, row + (kingMoves[i][1] * sign), col + (kingMoves[i][0] * sign), (byte) 0));
                    }
                    if (sign < 0 && board[col + kingMoves[i][0] * sign][row + kingMoves[i][1] * sign] > 0) {
                        //can capture pieces being protected too lol
                        moves.add(new Move(row, col, row + (kingMoves[i][1] * sign), col + (kingMoves[i][0] * sign), (byte) 1));
                    }
                    if (sign < 0 && board[col + kingMoves[i][1] * sign][row + kingMoves[i][1] * sign] > 0) {
                        moves.add(new Move(row, col, row + (kingMoves[i][1] * sign), col + (kingMoves[i][0] * sign), (byte) 1));
                    }
                }
            }
        }
        return moves;
    }
}
