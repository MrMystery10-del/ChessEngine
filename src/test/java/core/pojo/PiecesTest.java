package core.pojo;

import core.pojo.interfaces.Piece;
import core.pojo.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PiecesTest {

    private Piece bishop;
    private Piece rook;
    private Piece king;
    private Piece knight;
    private Piece pawn;
    private Piece queen;

    @BeforeEach
    void setup() {
        bishop = new Bishop(new Position(0, 0), Color.BLACK);
        rook = new Rook();
        king = new King();
        knight = new Knight();
        pawn = new Pawn();
        queen = new Queen();
    }

    @Test
    void doesExist() {
        assertNotNull(bishop);
        assertNotNull(rook);
        assertNotNull(king);
        assertNotNull(knight);
        assertNotNull(pawn);
        assertNotNull(queen);
    }
}