package chess.game.move.other;

import chess.game.board.Board;
import chess.game.move.Move;

public class NullMove extends Move {

    /**
     * Constructor for a NullMove object.
     */
    public NullMove() {
        super(null, null, -1);
    }

    @Override
    public Board executeMove() {
        throw new RuntimeException("Cannot execute a null chess.game.move!");
    }
}
