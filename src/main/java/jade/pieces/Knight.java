package jade.pieces;

import com.google.common.collect.ImmutableList;
import jade.Team;
import jade.board.Board;
import jade.board.Move;
import jade.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private final static int[] POSSIBLE_MOVES = { -17, -15, -10, -6, 6, 10, 15, 17 };

    Knight(int position, Team team) {
        super(position, team);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {

        int potentialDestination;
        final List<Move> legalMoves = new ArrayList<>();

        for (final int potentialMove : POSSIBLE_MOVES) {
            potentialDestination = this.position + potentialMove;
            if (true /* is valid coordinate */) {
                final Square destination = Board.getSquare(potentialDestination);
                if (!destination.isOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece occupier = destination.getPiece();
                    if (this.team != occupier.getTeam()) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}
