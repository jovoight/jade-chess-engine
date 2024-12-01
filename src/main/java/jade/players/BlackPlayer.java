package jade.players;

import jade.Team;
import jade.board.Board;
import jade.board.Move;
import jade.pieces.Piece;

import java.util.Collection;

public class BlackPlayer extends Player {
    public BlackPlayer(Board board, Collection<Move> blackLegalMoves, Collection<Move> whiteLegalMoves) {
        super(board, blackLegalMoves, whiteLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() { return board.getBlackPieces(); }
    @Override
    public Team getTeam() { return Team.BLACK; }
    @Override
    public Player getOpponent() { return board.getWhitePlayer(); }
}
