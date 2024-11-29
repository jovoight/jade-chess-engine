package jade.pieces;

import jade.Team;
import jade.board.Move;
import jade.board.Board;

import java.util.Collection;

public abstract class Piece {

    protected final int position;
    protected final Team team;

    Piece(final int position, final Team team) {
        this.position = position;
        this.team = team;
    }

    public Team getTeam() {
        return this.team;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);

}
