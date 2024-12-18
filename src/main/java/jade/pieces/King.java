package jade.pieces;

import com.google.common.collect.ImmutableList;
import jade.Team;
import jade.board.Board;
import jade.board.BoardUtils;
import jade.board.Move;
import jade.board.Square;
import static jade.board.Move.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {

    private final static int[] POSSIBLE_MOVES = { -9, -8, -7, -1, 1, 7, 8, 9 };

    public King(int position, Team team) {
        super(position, team, PieceType.KING);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int move: POSSIBLE_MOVES) {
            final int destination = this.position + move;
            // check if it is a valid position
            if (BoardUtils.isValidDestination(destination)) {
                // handle edge cases
                if (
                    isFirstColumnExclusion(this.position, move)
                    || isEighthColumnExclusion(this.position, move)
                ) { continue; }
                // check if occupied
                final Square destinationSquare = board.getSquare(destination);
                if (!destinationSquare.isOccupied()) { legalMoves.add(new NormalMove(board, this, destination)); }
                else {
                    final Piece occupier = destinationSquare.getPiece();
                    if (this.team != occupier.getTeam()) { legalMoves.add(new Capture(board, this, destination, occupier)); }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int position, final int move) {
        return BoardUtils.FIRST_COLUMN[position] && (move == -9 || move == -1 || move == 7);
    }
    private static boolean isEighthColumnExclusion(final int position, final int move) {
        return BoardUtils.EIGHTH_COLUMN[position] && (move == -7 || move == 1 || move == 9);
    }

    @Override
    public String toString() { return PieceType.KING.toString(); }
}
