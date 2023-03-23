import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Nooby {
    // pieces: pawn 1, knight 2, bishop 3, rook 4, queen 5, king 6. For black pieces same number just negative
    public byte[][] playNewMove(byte[][] board, boolean isWhiteTurn) {
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
        Collections.sort(possibleMoves, new Comparator<Move>() {
            @Override
            public int compare(Move m1, Move m2) {
                return Integer.compare(Math.abs(m2.getCapturedPiece()), Math.abs(m1.getCapturedPiece()));
            }
        });

        // Select the move with the highest value capture (if any), or else just the first move in the list
        Move selectedMove = possibleMoves.get(0);

        // Apply the selected move to the board and return the resulting state
        byte[][] newBoard = applyMove(board, selectedMove);
        return newBoard;
    }

    // Returns a list of all possible moves for the piece at the specified position on the board
    private List<Move> getPossibleMoves(byte[][] board, int row, int col) {
        List<Move> moves = new ArrayList<>();
        byte piece = board[row][col];
        int sign = (piece > 0) ? 1 : -1;

        // Check all possible moves for the piece, based on its type
        switch (Math.abs(piece)) {
            case 1: // Pawn
                // TODO: Implement pawn moves
                break;
            case 2: // Knight
                // TODO: Implement knight moves
                break;
            case 3: // Bishop
                // TODO: Implement bishop moves
                break;
            case 4: // Rook
                // TODO: Implement rook moves
                break;
            case 5: // Queen
                // TODO: Implement queen moves
                break;
            case 6: // King
                // TODO: Implement king moves
                break;
        }

        return moves;
    }

    // Applies the specified move to the board and returns the resulting state
    private byte[][] applyMove(byte[][] board, Move move) {
        // Create a copy of the board to modify
        byte[][] newBoard = new byte[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, 8);
        }
        // Apply the move to the new board
        int fromRow = move.getFromRow();
        int fromCol = move.getFromCol();
        int toRow = move.getToRow();
        int toCol = move.getToCol();
        byte piece = newBoard[fromRow][fromCol];

        // Check if this is a castling move
        if (Math.abs(piece) == 6 && Math.abs(fromCol - toCol) == 2) {
            // This is a castling move, so move the king and rook accordingly
            newBoard[fromRow][fromCol] = 0;
            if (toCol == 2) {
                // Queenside castling
                newBoard[fromRow][fromCol - 2] = piece;
                newBoard[fromRow][0] = 0;
                newBoard[fromRow][fromCol - 1] = (byte) (piece > 0 ? 4 : -4);
            } else {
                // King side castling
                newBoard[fromRow][fromCol + 2] = piece;
                newBoard[fromRow][7] = 0;
                newBoard[fromRow][fromCol + 1] = (byte) (piece > 0 ? 4 : -4);
            }
        } else {
            // This is not a castling move, so just move the piece
            newBoard[fromRow][fromCol] = 0;
            newBoard[toRow][toCol] = piece;

            // Check if this is a pawn promotion move
            if (Math.abs(piece) == 1 && (toRow == 0 || toRow == 7)) {
                // This is a pawn promotion move, so replace the pawn with a queen
                newBoard[toRow][toCol] = (byte) (piece > 0 ? 5 : -5);
            }
        }

        return newBoard;
    }

    // Inner class representing a move on the board
    private static class Move {
        private int fromRow, fromCol, toRow, toCol, capturedPiece;

        public Move(int fromRow, int fromCol, int toRow, int toCol, int capturedPiece) {
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

        public int getCapturedPiece() {
            return capturedPiece;
        }
    }
}