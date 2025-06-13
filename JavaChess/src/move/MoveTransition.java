package move;

import board.Board;

/**
 * This class deals with tracking the board before the move was made and after the move was made,
 * as well as if the move was successfully made or not
 */
public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    /**
     * Constructor for a MoveTransition object.
     * @param transitionBoard the board to transition to
     * @param move the move affecting the board
     * @param moveStatus the status of the move
     */
    public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    /**
     * @return the move status
     */
    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }
}
