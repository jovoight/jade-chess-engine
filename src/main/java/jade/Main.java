package jade;

import jade.board.Board;

public class Main {
    public static void main(String[] args) {
        Board board = Board.buildStandardBoard();
        System.out.println(board);
    }
}
