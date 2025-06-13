package main;

import board.Board;

/**
 * This is the driver class to run the chess game.
 */
public class Main {
    public static void main(String[] args) {
        Board board = Board.CreateInitialBoard();

        System.out.println(board);
    }
}
