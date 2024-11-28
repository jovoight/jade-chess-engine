package jade;

// square for the chess board
public abstract class Square {
    int index;
    Square(int index) { this.index = index; }
    public abstract boolean isOccupied();
    public abstract Piece getPiece();

    public static final class EmptySquare extends Square {
        EmptySquare(int index) {
            super(index);
        }
        @Override
        public boolean isOccupied() { return false; }
        @Override
        public Piece getPiece() { return null; }
    }

    public static final class OccupiedSquare extends Square {
        Piece piece;
        OccupiedSquare(int index, Piece piece) {
            super(index);
            this.piece = piece;
        }
        @Override
        public boolean isOccupied() { return true; }
        @Override
        public Piece getPiece() { return this.piece; }
    }
}
