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

public class Knight extends Piece {

    private final static int[] POSSIBLE_MOVES = { -17, -15, -10, -6, 6, 10, 15, 17 };

    public Knight(int position, Team team) { super(position, team, PieceType.KNIGHT); }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int move: POSSIBLE_MOVES) {
            final int destination = this.position + move;
            // check if it is a valid position
            if (BoardUtils.isValidDestination(destination)) {
                // handle edge cases
                if (
                    isFirstColumnExclusion(this.position, move)
                    ||isSecondColumnExclusion(this.position, move)
                    ||isSeventhColumnExclusion(this.position, move)
                    ||isEighthColumnExclusion(this.position, move)
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
        return BoardUtils.FIRST_COLUMN[position] && (move == -17 || move == -10 || move == 6 || move == 15);
    }
    private static boolean isSecondColumnExclusion(final int position, final int move) {
        return BoardUtils.SECOND_COLUMN[position] && (move == -10 || move == 6);
    }
    private static boolean isSeventhColumnExclusion(final int position, final int move) {
        return BoardUtils.SEVENTH_COLUMN[position] && (move == 10 || move == -6);
    }
    private static boolean isEighthColumnExclusion(final int position, final int move) {
        return BoardUtils.EIGHTH_COLUMN[position] && (move == 17 || move == 10 || move == -6 || move == -15);
    }

    @Override
    public String toString() { return PieceType.KNIGHT.toString(); }
}
