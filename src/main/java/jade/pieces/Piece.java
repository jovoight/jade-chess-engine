package jade.pieces;

import jade.Team;
import jade.board.Move;
import jade.board.Board;

import java.util.Collection;

public abstract class Piece {

    protected final int position;
    protected final Team team;
    protected final boolean isFirstMove;

    Piece(final int position, final Team team) {
        this.position = position;
        this.team = team;
        this.isFirstMove = true;
    }

    public Team getTeam() { return this.team; }
    public boolean isFirstMove() { return this.isFirstMove; }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
