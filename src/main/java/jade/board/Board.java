package jade.board;

import com.google.common.collect.ImmutableList;
import jade.Team;
import jade.pieces.*;
import jade.players.BlackPlayer;
import jade.players.Player;
import jade.players.WhitePlayer;

import java.util.*;

public class Board {

    private final List<Square> squares;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player activePlayer;

    private Board(BoardBuilder builder) {
        squares = populateSquares(builder);
        whitePieces = getActivePieces(squares, Team.WHITE);
        blackPieces = getActivePieces(squares, Team.BLACK);

        final Collection<Move> whiteLegalMoves = calculateLegalMoves(whitePieces);
        final Collection<Move> blackLegalMoves = calculateLegalMoves(blackPieces);
        whitePlayer = new WhitePlayer(this, whiteLegalMoves, blackLegalMoves);
        blackPlayer = new BlackPlayer(this, whiteLegalMoves, blackLegalMoves);
        activePlayer = null;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            final String squareString = squares.get(i).toString();
            builder.append(String.format("%3s", squareString));
            if ((i + 1) % 8 == 0) { builder.append("\n"); }
        }
        return builder.toString();
    }

    private Collection<Move> calculateLegalMoves(Collection<Piece> pieces) {
        final List<Move> legalMoves = new ArrayList<>();
        for (Piece piece : pieces) { legalMoves.addAll(piece.calculateLegalMoves(this)); }
        return ImmutableList.copyOf(legalMoves);
    }

    public Square getSquare(final int index) { return squares.get(index); }
    public Player getActivePlayer() { return activePlayer; }

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

    public Collection<Piece> getWhitePieces() { return whitePieces; }
    public Collection<Piece> getBlackPieces() { return blackPieces; }
    public WhitePlayer getWhitePlayer() { return whitePlayer; }
    public BlackPlayer getBlackPlayer() { return blackPlayer; }

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

        public BoardBuilder() { boardConfig = new HashMap<>(); }

        public BoardBuilder setPiece(final Piece piece) { this.boardConfig.put(piece.getPosition(), piece); return this; }
        public BoardBuilder setTurn(final Team turn) { this.turn = turn; return this; }

        public Board build() {
            return new Board(this);
        }
    }
}
