package jade.board;

import jade.Team;
import jade.pieces.Bishop;

public class Board {
    public static Square getSquare(final int index) {
        return Square.createSquare(index, new Bishop(0, Team.WHITE));
    }
}
