package jade.pieces;

import jade.Team;
import jade.board.Board;
import jade.board.BoardUtils;
import jade.board.Move;
import jade.board.Square;
import static jade.board.Move.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {

    private final static int[] POSSIBLE_MOVES = { -9, -7, 7, 9 };

    public Rook(int position, Team team) {
        super(position, team);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int move: POSSIBLE_MOVES) {
            int destination = this.position;
            while (BoardUtils.isValidDestination(destination)) {
                // handle edge cases
                if (
                    isFirstColumnExclusion(destination, move)
                    || isEighthColumnExclusion(destination, move)
                ) { break; }
                destination += move;
                if (BoardUtils.isValidDestination(destination)) {
                    // check if occupied
                    final Square destinationSquare = Board.getSquare(destination);
                    if (!destinationSquare.isOccupied()) { legalMoves.add(new NormalMove(board, this, destination)); }
                    else {
                        final Piece occupier = destinationSquare.getPiece();
                        if (this.team != occupier.getTeam()) { legalMoves.add(new Capture(board, this, destination, occupier)); }
                        break;
                    }
                }
            }
        }
        return legalMoves;
    }

    private static boolean isFirstColumnExclusion(final int position, final int move) {
        return BoardUtils.FIRST_COLUMN[position] && (move == -9 || move == 7);
    }
    private static boolean isEighthColumnExclusion(final int position, final int move) {
        return BoardUtils.EIGHTH_COLUMN[position] && (move == 9 || move == -7);
    }
}