package core.pojo;

import utils.Pieces;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class ChessPiece {

    protected Color color;
    protected Pieces pieceType;
    protected Position position;
    protected List<Position> validMoves;

    public ChessPiece(Position position, Pieces piece) {
        this.position = position;
        validMoves = new ArrayList<>();
        pieceType = piece;
    }
    
    /**
     * Eliminates the out-of-bounds pieces that have been calculated on location of the piece
     *
     * @param possibleMoves List of possible moves
     */

    protected void processMoves(List<Position> possibleMoves) {
        for (Position move : possibleMoves) {
            if (move.x() >= 0 && move.x() < 8 && move.y() >= 0 && move.y() < 8) {
                validMoves.add(move);
            }
        }
    }

    public List<Position> getValidMoves() {
        List<Position> possibleMoves = new ArrayList<>();

        if (pieceType == Pieces.PAWN) {
            int forwardDirection = (color == Color.WHITE) ? -1 : 1;
            int startingRank = (color == Color.WHITE) ? 6 : 1;
            int currentRank = position.y();

            // Check one square forward
            Position forwardPosition = new Position(position.x(), currentRank + forwardDirection);
            possibleMoves.add(forwardPosition);

            // Check two squares forward from the starting rank
            if (currentRank == startingRank) {
                Position twoSquaresForwardPosition = new Position(position.x(), currentRank + 2 * forwardDirection);
                possibleMoves.add(twoSquaresForwardPosition);
            }

            // Check diagonal captures
            Position capturePosition1 = new Position(position.x() - 1, currentRank + forwardDirection);
            Position capturePosition2 = new Position(position.x() + 1, currentRank + forwardDirection);
            possibleMoves.add(capturePosition1);
            possibleMoves.add(capturePosition2);
        }

        // ... additional code for calculating possible moves for other piece types ...

        // Check if the piece is a pawn and has reached the last rank
        if (pieceType == Pieces.PAWN && (position.y() == 0 || position.y() == 7)) {
            // Prompt the player to select a piece for evolution
            Pieces newPieceType = promptPieceEvolution();

            // Update the piece type to the selected piece
            if (newPieceType != null) {
                pieceType = newPieceType;
            }
        }

        processMoves(possibleMoves);
        return validMoves;
    }

    protected Pieces promptPieceEvolution() {
        System.out.println("Pawn reached the last rank. Select a piece for evolution:");
        System.out.println("1. Queen");
        System.out.println("2. Rook");
        System.out.println("3. Bishop");
        System.out.println("4. Knight");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return Pieces.QUEEN;
            case 2:
                return Pieces.ROOK;
            case 3:
                return Pieces.BISHOP;
            case 4:
                return Pieces.KNIGHT;
            default:
                return null;
        }
    }
}

enum Pieces {
    PAWN,
    QUEEN,
    ROOK,
    BISHOP,
    KNIGHT
}

class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
