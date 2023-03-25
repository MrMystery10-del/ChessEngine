package bots.util;

import java.util.ArrayList;
import java.util.List;

public class Util
{
    private static boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public static byte[][] deepCopy(byte[][] board) {
        byte[][] newBoard = new byte[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    public static byte[][] applyMove(byte[][] board, Move move) {
        byte[][] newBoard = Util.deepCopy(board);
        int startRow = move.getFromRow();
        int startCol = move.getFromCol();
        int endRow = move.getToRow();
        int endCol = move.getToCol();
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
}
