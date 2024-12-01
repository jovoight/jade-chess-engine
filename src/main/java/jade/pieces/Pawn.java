package jade.pieces;

import com.google.common.collect.ImmutableList;
import jade.Team;
import jade.board.Board;
import jade.board.BoardUtils;
import jade.board.Move;
import static jade.board.Move.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] POSSIBLE_MOVES = { 7, 8, 9, 16 };

    Pawn(int position, Team team) {
        super(position, team);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int move : POSSIBLE_MOVES) {
            final int destination = this.position + this.getTeam().getDirection() * move;
            if (!BoardUtils.isValidDestination(destination)) { continue; }
            // TODO: change to PawnMove class
            // normal single square move
            if (move == 8 && !board.getSquare(destination).isOccupied()) {
                legalMoves.add(new NormalMove(board, this, destination));
            } else if ( // double square move
                move == 16 && this.isFirstMove()
                && ((BoardUtils.SECOND_ROW[this.position] && this.getTeam().isBlack())
                || (BoardUtils.SEVENTH_ROW[this.position] && this.getTeam().isWhite()))
            ) {
                final int between = this.position + this.getTeam().getDirection() * 8;
                if (!board.getSquare(between).isOccupied() && !board.getSquare(destination).isOccupied()) {
                    legalMoves.add(new NormalMove(board, this, destination));
                }
            } else if ( // captures
                ((move == 7
                && !(BoardUtils.EIGHTH_COLUMN[this.position] && this.getTeam().isWhite()
                || BoardUtils.FIRST_COLUMN[this.position] && this.getTeam().isBlack()))
                || (move == 9
                && !(BoardUtils.FIRST_COLUMN[this.position] && this.getTeam().isWhite()
                || BoardUtils.EIGHTH_COLUMN[this.position] && this.getTeam().isBlack())))
                && board.getSquare(destination).isOccupied()
            ) {
                final Piece occupier = board.getSquare(destination).getPiece();
                if (occupier.getTeam() != this.getTeam()) { legalMoves.add(new Capture(board, this, destination, occupier)); }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
}
