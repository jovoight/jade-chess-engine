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

    public static final class NormalMove extends Move {
        public NormalMove(final Board board, final Piece piece, final int destination) {
            super(board, piece, destination);
        }
    }

    public static final class Capture extends Move {
        final Piece captured;
        public Capture(final Board board, final Piece piece, final int destination, final Piece captured) {
            super(board, piece, destination);
            this.captured = captured;
        }
    }
}
