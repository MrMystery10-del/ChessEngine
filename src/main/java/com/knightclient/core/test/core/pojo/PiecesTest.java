package com.knightclient.core.test.core.pojo;

import com.knightclient.core.pojo.Piece;
import com.knightclient.core.pojo.pieces.*;
import com.knightclient.core.pojo.pieces.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        bishop = new Bishop();
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