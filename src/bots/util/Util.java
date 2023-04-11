package bots.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
    private static boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
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

    public static List<Move> getPossibleMoves(byte[][] board, int row, int col) {
        List<Move> moves = new ArrayList<>();
        byte piece = board[row][col];
        int sign = (piece > 0) ? 1 : -1;

        // Check all possible moves for the piece, based on its type
        switch (Math.abs(piece)) {
            case 1 -> moves.addAll(getPawnMoves(board, row, col, sign));
            case 2 -> moves.addAll(getKnightMoves(board, row, col, sign));
            case 3 -> moves.addAll(getBishopMoves(board, row, col, sign));
            case 4 -> moves.addAll(getRockMoves(board, row, col, sign));
            case 5 -> moves.addAll(getQueenMoves(board, row, col, sign));
            case 6 -> moves.addAll(getKingMoves(board, row, col, sign));
        }
        return moves;
    }

    private static List<Move> getPawnMoves(byte[][] board, int row, int col, int sign) {
        List<Move> moves = new ArrayList<>();

        int start_location_black = 1;
        int start_location_white = 6;

        // Check one square forward
        int newRow = row + (-1 * sign);
        if (isValidPosition(newRow, col) && board[newRow][col] == 0)
            moves.add(new Move(row, col, newRow, col, (byte) 0));

        // Check two squares forward
        newRow = row + (-2 * sign);
        if (isValidPosition(newRow, col) && board[newRow][col] == 0 && board[newRow + sign][row] == 0) {
            if (sign == -1 && row == start_location_black)
                moves.add(new Move(row, col, newRow, col, (byte) 0));
            else if (sign == 1 && row == start_location_white)
                moves.add(new Move(row, col, newRow, col, (byte) 0));
        }

        // Check for capturing to the left
        int newCol = col + (-1 * sign);
        newRow = row + (-1 * sign);
        if (isValidPosition(newRow, newCol)) {
            byte target_piece = board[newRow][newCol];
            if (target_piece != 0 && ((sign < 0 && target_piece > 0) || (sign > 0 && target_piece < 0)))
                moves.add(new Move(row, col, newRow, newCol, (byte) Math.abs(target_piece)));
        }

        // Check for capturing to the right
        newCol = col + (1 * sign);
        newRow = row + (-1 * sign);
        if (isValidPosition(newRow, newCol)) {
            byte target_piece = board[newRow][newCol];
            if (target_piece != 0 && ((sign < 0 && target_piece > 0) || (sign > 0 && target_piece < 0)))
                moves.add(new Move(row, col, newRow, newCol, (byte) Math.abs(target_piece)));
        }
        return moves;
    }

    private static List<Move> getKnightMoves(byte[][] board, int row, int col, int sign) {
        List<Move> moves = new ArrayList<>();

        int[][] Moves = {{2, -1}, {2, 1}, {1, 2}, {1, -2}, {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}};
        for (int i = 0; i < Moves.length; i++) {
            int newRow = row + (Moves[i][1] * sign);
            int newCol = col + (Moves[i][0] * sign);

            if (!isValidPosition(newRow, newCol)) continue;

            int signOfEnemy = board[newRow][newCol] > 0 ? 1 : -1;
            if (signOfEnemy != sign)
                moves.add(new Move(row, col, newRow, newCol, board[newRow][newCol]));
        }
        return moves;
    }

    private static List<Move> getBishopMoves(byte[][] board, int row, int col, int sign) {
        int[][] moves = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

        return getLongMoves(board, row, col, sign, moves);
    }

    private static List<Move> getRockMoves(byte[][] board, int row, int col, int sign) {
        int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        return getLongMoves(board, row, col, sign, moves);
    }

    private static List<Move> getQueenMoves(byte[][] board, int row, int col, int sign) {
        int[][] moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

        return getLongMoves(board, row, col, sign, moves);
    }

    private static List<Move> getKingMoves(byte[][] board, int row, int col, int sign) {
        List<Move> moves = new ArrayList<>();

        int[][] Moves = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        for (int i = 0; i < Moves.length; i++) {

            int newRow = row + Moves[i][0];
            int newCol = col + Moves[i][1];

            if (isValidPosition(newRow, newCol)) {
                if (board[newRow][newCol] == 0)
                    moves.add(new Move(row, col, newRow, newCol, (byte) 0));
                else if ((sign < 0 && board[newRow][newCol] > 0) || (sign > 0 && board[newRow][newCol] < 0))
                    moves.add(new Move(row, col, newRow, newCol, board[newRow][newCol]));
            }
        }

        return moves;
    }

    private static List<Move> getLongMoves(byte[][] board, int row, int col, int sign, int[][] lines) {
        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {

            int newRow = row + (lines[i][1] * sign);
            int newCol = col + (lines[i][0] * sign);

            while (isValidPosition(newRow, newCol)) {
                if (board[newRow][newCol] == 0)
                    moves.add(new Move(row, col, newRow, newCol, (byte) 0));
                else if ((sign < 0 && board[newRow][newCol] > 0) || (sign > 0 && board[newRow][newCol] < 0)) {
                    moves.add(new Move(row, col, newRow, newCol, (byte) 1));
                    break;
                } else break;
                newRow += (lines[i][1] * sign);
                newCol += (lines[i][0] * sign);
            }
        }
        return moves;
    }

    private static void checkCheckMate(int sign, byte[][] board) {
        int rowKing;//row location of king
        int colKing;//col location of king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 6 * sign) {
                    rowKing = i;
                    colKing = j;
                }
            }
        }
    }
}