package jade.pieces;

import jade.Team;
import jade.board.Move;
import jade.board.Board;

import java.util.Collection;

public abstract class Piece {

    protected final int position;
    protected final Team team;
    protected final boolean isFirstMove;
    protected final PieceType pieceType;

    Piece(final int position, final Team team, final PieceType pieceType) {
        this.position = position;
        this.team = team;
        this.isFirstMove = true;
        this.pieceType = pieceType;
    }

    public Team getTeam() { return team; }
    public int getPosition() { return position; }
    public boolean isFirstMove() { return isFirstMove; }
    public PieceType getPieceType() { return pieceType; }
    public enum PieceType {
        PAWN("P"),
        KNIGHT("N"),
        ROOK("R"),
        QUEEN("Q"),
        KING("K"),
        BISHOP("B");

        private String pieceName;

        PieceType(final String pieceName) { this.pieceName = pieceName; }

        @Override
        public String toString() { return pieceName; }
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
