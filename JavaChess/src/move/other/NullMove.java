package move.other;

import board.Board;
import move.Move;
import piece.Piece;

public class NullMove extends Move {

    /**
     * Constructor for a NullMove object.
     */
    public NullMove() {
        super(null, null, -1);
    }

    @Override
    public Board executeMove() {
        throw new RuntimeException("Cannot execute a null move!");
    }
}
