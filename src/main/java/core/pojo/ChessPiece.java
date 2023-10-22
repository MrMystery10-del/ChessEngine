package core.pojo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class ChessPiece {

    protected Color color;
    protected Pieces pieceType;
    protected ChessPosition position;
    protected List<ChessPosition> validMoves; // 使用 ChessPosition
    private Scanner scanner;

    public ChessPiece(ChessPosition position, Pieces piece) {
        this.position = position;
        this.validMoves = new ArrayList<>();
        this.pieceType = piece;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Eliminates the out-of-bounds pieces that have been calculated on location of the piece
     *
     * @param possibleMoves List of possible moves
     */
    protected void processMoves(List<ChessPosition> possibleMoves) {
        for (ChessPosition move : possibleMoves) {
            if (move.x() >= 0 && move.x() < 8 && move.y() >= 0 && move.y() < 8) {
                validMoves.add(move);
            }
        }
    }

    public List<ChessPosition> getValidMoves() {
        List<ChessPosition> possibleMoves = new ArrayList<>();

        if (pieceType == Pieces.PAWN) {
            int forwardDirection = (color == Color.WHITE) ? -1 : 1;
            int startingRank = (color == Color.WHITE) ? 6 : 1;
            int currentRank = position.y();

            // 使用 ChessPosition
            ChessPosition forwardPosition = new ChessPosition(position.x(), currentRank + forwardDirection);
            possibleMoves.add(forwardPosition);

            // 使用 ChessPosition
            if (currentRank == startingRank) {
                ChessPosition twoSquaresForwardPosition = new ChessPosition(position.x(), currentRank + 2 * forwardDirection);
                possibleMoves.add(twoSquaresForwardPosition);
            }

            // 使用 ChessPosition
            ChessPosition capturePosition1 = new ChessPosition(position.x() - 1, currentRank + forwardDirection);
            ChessPosition capturePosition2 = new ChessPosition(position.x() + 1, currentRank + forwardDirection);
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

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
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

class ChessPosition {
    private int x;
    private int y;

    public ChessPosition(int x, int y) {
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
