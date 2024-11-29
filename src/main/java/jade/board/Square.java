package jade.board;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;


import jade.pieces.Piece;


// square for the chess board
public abstract class Square {

    protected final int index;
    public abstract boolean isOccupied();
    public abstract Piece getPiece();
    private static final Map<Integer, EmptySquare> EMPTY_SQUARE_CACHE = initializeEmptySquares();

    private Square(final int index) { this.index = index; }

    // create an immutable hashmap of empty squares
    private static Map<Integer, EmptySquare> initializeEmptySquares() {
        final Map<Integer, EmptySquare> emptySquareMap = new HashMap<>();
        for (int index = 0; index < 64; index++) {
            emptySquareMap.put(index, new EmptySquare(index));
        }
        return ImmutableMap.copyOf(emptySquareMap);
    }

    // create a new occupied square or get the existing empty square
    public static Square createSquare(final int index, final Piece piece) {
        return piece != null ? new OccupiedSquare(index, piece) : EMPTY_SQUARE_CACHE.get(index);
    }

    // empty square subclass
    public static final class EmptySquare extends Square {
        private EmptySquare(final int index) {
            super(index);
        }
        @Override
        public boolean isOccupied() { return false; }
        @Override
        public Piece getPiece() { return null; }
    }

    // occupied square subclass
    public static final class OccupiedSquare extends Square {
        private final Piece piece;
        private OccupiedSquare(int index, final Piece piece) {
            super(index);
            this.piece = piece;
        }
        @Override
        public boolean isOccupied() { return true; }
        @Override
        public Piece getPiece() { return this.piece; }
    }
}
