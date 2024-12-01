package jade.players;

import jade.Team;
import jade.board.Board;
import jade.board.Move;
import jade.pieces.King;
import jade.pieces.Piece;
import static jade.pieces.Piece.*;

import java.util.Collection;

public abstract class Player {
    protected final Board board;
    protected final King king;
    protected final Collection<Move> legalMoves;
    protected final Collection<Move> opponentLegalMoves;

    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentLegalMoves) {
        this.board = board;
        this.legalMoves = legalMoves;
        this.opponentLegalMoves = opponentLegalMoves;
        king = findKing();
    }

    private King findKing() {
        for (final Piece piece : getActivePieces()) { if (piece.getPieceType() == PieceType.KING) { return (King) piece; }}
        throw new RuntimeException("Illegal position: no king found");
    }
    public King getKing() { return king; }
    public Collection<Move> getLegalMoves() { return legalMoves; }

    public boolean hasLegalMove(final Move move) { return legalMoves.contains(move); }
    public boolean isInCheck() {
        for (final Move move : opponentLegalMoves) { if (move.getDestination() == king.getPosition()) { return true; }}
        return false;
    }
    public boolean hasEscapeMove() {
        for (final Move move : legalMoves) {
            final MoveTransition transition = makeMove(move);
            if (transition.getStatus().isDone()) { return true; }
        }
        return false;
    }
    public boolean isInCheckmate() { return isInCheck() && !hasEscapeMove(); }
    public boolean isInStalemate() { return !isInCheck() && !hasEscapeMove(); }
    public boolean isCastled() { return false; }
    public MoveTransition makeMove(final Move move) {
        // move is for sure illegal
        if (!hasLegalMove(move)) { return new MoveTransition(board, move, MoveStatus.ILLEGAL); }
        // test the move on another board
        final Board transitionBoard = move.execute();
        // move puts the king in check - also illegal
        if (transitionBoard.getActivePlayer().getOpponent().isInCheck()) { return new MoveTransition(board, move, MoveStatus.ILLEGAL); }
        // otherwise the move is legal
        return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Team getTeam();
    public abstract Player getOpponent();
}
