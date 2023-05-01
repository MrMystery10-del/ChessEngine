package bots.util;

import java.util.ArrayList;
import java.util.List;

public class Util {

    private static boolean whiteQueenCastle = true;
    private static boolean whiteKingCastle = true;
    private static boolean blackQueenCastle = true;
    private static boolean blackKingCastle = true;

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

        checkCastlingBools(board);

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
        newCol = col + (sign);
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
        for (int[] move : Moves) {
            int newRow = row + (move[1] * sign);
            int newCol = col + (move[0] * sign);

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
        for (int[] move : Moves) {

            int newRow = row + move[0];
            int newCol = col + move[1];

            if (isValidPosition(newRow, newCol)) {
                if (board[newRow][newCol] == 0)
                    moves.add(new Move(row, col, newRow, newCol, (byte) 0));
                else if ((sign < 0 && board[newRow][newCol] > 0) || (sign > 0 && board[newRow][newCol] < 0))
                    moves.add(new Move(row, col, newRow, newCol, board[newRow][newCol]));
            }
        }

        //Black king and queen side castling check
        if(sign == -1 && getBlackKingCastle()){
            if(board[row][5] == 0 && board[row][6] == 0){
                moves.add(new Move(row, col, row, 6, board[row][6]));
            }
        }

        if(sign == -1 && getBlackQueenCastle()){
            if(board[row][1] == 0 && board[row][2] == 0 && board[row][3] == 0){
                moves.add(new Move(row, col, row, 2, board[row][2]));
            }
        }

        //White king and queen side castling check
        if(sign == 1 && getWhiteKingCastle()){
            if(board[row][5] == 0 && board[row][6] == 0){
                moves.add(new Move(row, col, row, 6, board[row][6]));
            }
        }

        if(sign == 1 && getWhiteQueenCastle()){
            if(board[row][1] == 0 && board[row][2] == 0 && board[row][3] == 0){
                moves.add(new Move(row, col, row, 2, board[row][2]));
            }
        }


        return moves;
    }

    private static List<Move> getLongMoves(byte[][] board, int row, int col, int sign, int[][] lines) {
        List<Move> moves = new ArrayList<>();

        for (int[] line : lines) {

            int newRow = row + (line[1] * sign);
            int newCol = col + (line[0] * sign);

            while (isValidPosition(newRow, newCol)) {
                if (board[newRow][newCol] == 0)
                    moves.add(new Move(row, col, newRow, newCol, (byte) 0));
                else if ((sign < 0 && board[newRow][newCol] > 0) || (sign > 0 && board[newRow][newCol] < 0)) {
                    moves.add(new Move(row, col, newRow, newCol, (byte) 1));
                    break;
                } else break;
                newRow += (line[1] * sign);
                newCol += (line[0] * sign);
            }
        }
        return moves;
    }

    public static boolean getWhiteQueenCastle() {return whiteQueenCastle;}

    public static boolean getWhiteKingCastle() {return whiteKingCastle;}

    public static boolean getBlackQueenCastle() {return blackQueenCastle;}

    public static boolean getBlackKingCastle() {return blackKingCastle;}

    public static void setWhiteQueenCastle(boolean value) {whiteQueenCastle = value;}

    public static void setWhiteKingCastle(boolean value) {whiteKingCastle = value;}
    public static void setBlackQueenCastle(boolean value) {blackQueenCastle = value;}

    public static void setBlackKingCastle(boolean value) {blackKingCastle = value;}

    private static void checkCastlingBools(byte[][] board){

        int blackRow = 0;

        if(board[blackRow][0] != -4){ setBlackQueenCastle(false);}
        if(board[blackRow][7] != -4){ setBlackKingCastle(false);}

        if(board[blackRow][4] != -6){
            setBlackQueenCastle(false);
            setBlackKingCastle(false);
        }

        int whiteRow = 7;

        if(board[whiteRow][0] !=  4){ setWhiteQueenCastle(false);}
        if(board[whiteRow][7] !=  4){ setWhiteKingCastle(false);}

        if(board[whiteRow][4] !=  6){
            setWhiteQueenCastle(false);
            setWhiteKingCastle(false);
        }

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