package jade.players;

import jade.board.Board;
import jade.board.Move;

public class MoveTransition {
    private final Board newBoard;
    private final Move move;
    private final MoveStatus status;

    public MoveTransition(final Board newBoard, final Move move, final MoveStatus status) {
        this.newBoard = newBoard;
        this.move = move;
        this.status = status;
    }

    public MoveStatus getStatus() { return status; }
}
