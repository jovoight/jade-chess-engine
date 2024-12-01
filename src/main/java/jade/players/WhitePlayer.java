package jade.players;

import jade.Team;
import jade.board.Board;
import jade.board.Move;
import jade.pieces.Piece;

import java.util.Collection;

public class WhitePlayer extends Player {

    public WhitePlayer(Board board, Collection<Move> whiteLegalMoves, Collection<Move> blackLegalMoves) {
        super(board, whiteLegalMoves, blackLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() { return board.getWhitePieces(); }
    @Override
    public Team getTeam() { return Team.WHITE; }
    @Override
    public Player getOpponent() { return board.getBlackPlayer(); }
}
