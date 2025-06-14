package chess.game.move;

import chess.game.board.Board;

/**
 * This class deals with tracking the chess.game.board before the chess.game.move was made and after the chess.game.move was made,
 * as well as if the chess.game.move was successfully made or not
 */
public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    /**
     * Constructor for a MoveTransition object.
     * @param transitionBoard the chess.game.board to transition to
     * @param move the chess.game.move affecting the chess.game.board
     * @param moveStatus the status of the chess.game.move
     */
    public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    /**
     * @return the chess.game.move status
     */
    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }
}
