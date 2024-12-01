package jade.board;

import com.google.common.collect.ImmutableList;
import jade.Team;
import jade.pieces.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Board {

    private final List<Square> squares;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;

    private Board(BoardBuilder builder) {
        this.squares = populateSquares(builder);
        this.whitePieces = getActivePieces(this.squares, Team.WHITE);
        this.blackPieces = getActivePieces(this.squares, Team.BLACK);
    }

    public Square getSquare(final int index) {
        return squares.get(index);
    }

    private static List<Square> populateSquares(final BoardBuilder builder) {
        final Square[] squares = new Square[64];
        for (int i = 0; i < 64; i++) { squares[i] = Square.createSquare(i, builder.boardConfig.get(i)); }
        return ImmutableList.copyOf(squares);
    }

    private static Collection<Piece> getActivePieces(final List<Square> squares, final Team team) {
        final List<Piece> activePieces = new ArrayList<>();
        for (final Square square : squares) {
            if (square.isOccupied()) {
                final Piece piece = square.getPiece();
                if (piece.getTeam() == team) { activePieces.add(piece); }
            }
        }
        return ImmutableList.copyOf(activePieces);
    }

    public static Board buildStandardBoard() {

        final BoardBuilder builder = new BoardBuilder();

        // Black pieces
        builder.setPiece(new Rook(0, Team.BLACK));
        builder.setPiece(new Knight(1, Team.BLACK));
        builder.setPiece(new Bishop(2, Team.BLACK));
        builder.setPiece(new Queen(3, Team.BLACK));
        builder.setPiece(new King(4, Team.BLACK));
        builder.setPiece(new Bishop(5, Team.BLACK));
        builder.setPiece(new Knight(6, Team.BLACK));
        builder.setPiece(new Rook(7, Team.BLACK));
        builder.setPiece(new Pawn(8, Team.BLACK));
        builder.setPiece(new Pawn(9, Team.BLACK));
        builder.setPiece(new Pawn(10, Team.BLACK));
        builder.setPiece(new Pawn(11, Team.BLACK));
        builder.setPiece(new Pawn(12, Team.BLACK));
        builder.setPiece(new Pawn(13, Team.BLACK));
        builder.setPiece(new Pawn(14, Team.BLACK));
        builder.setPiece(new Pawn(15, Team.BLACK));

        // White pieces
        builder.setPiece(new Pawn(48, Team.WHITE));
        builder.setPiece(new Pawn(49, Team.WHITE));
        builder.setPiece(new Pawn(50, Team.WHITE));
        builder.setPiece(new Pawn(51, Team.WHITE));
        builder.setPiece(new Pawn(52, Team.WHITE));
        builder.setPiece(new Pawn(53, Team.WHITE));
        builder.setPiece(new Pawn(54, Team.WHITE));
        builder.setPiece(new Pawn(55, Team.WHITE));
        builder.setPiece(new Rook(56, Team.WHITE));
        builder.setPiece(new Knight(57, Team.WHITE));
        builder.setPiece(new Bishop(58, Team.WHITE));
        builder.setPiece(new Queen(59, Team.WHITE));
        builder.setPiece(new King(60, Team.WHITE));
        builder.setPiece(new Bishop(61, Team.WHITE));
        builder.setPiece(new Knight(62, Team.WHITE));
        builder.setPiece(new Rook(63, Team.WHITE));

        // White goes first
        builder.setTurn(Team.WHITE);

        return builder.build();
    }

    public static class BoardBuilder {

        Map<Integer, Piece> boardConfig;
        Team turn;

        public BoardBuilder() {}

        public BoardBuilder setPiece(final Piece piece) { this.boardConfig.put(piece.getPosition(), piece); return this; }
        public BoardBuilder setTurn(final Team turn) { this.turn = turn; return this; }

        public Board build() {
            return new Board(this);
        }
    }
}
