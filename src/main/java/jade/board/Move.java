package jade.board;

import jade.pieces.Piece;

public abstract class Move {
    final Board board;
    final Piece piece;
    final int destination;

    private Move(final Board board, final Piece piece, final int destination) {
        this.board = board;
        this.piece = piece;
        this.destination = destination;
    }

    public int getDestination() { return destination; }
    public abstract Board execute();

    public static final class NormalMove extends Move {
        public NormalMove(final Board board, final Piece piece, final int destination) { super(board, piece, destination); }
        @Override
        public Board execute() { return null; }
    }

    public static final class Capture extends Move {
        final Piece captured;
        public Capture(final Board board, final Piece piece, final int destination, final Piece captured) {
            super(board, piece, destination);
            this.captured = captured;
        }
        @Override
        public Board execute() { return null; }
    }
}
