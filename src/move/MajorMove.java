package move;

import board.Board;
import piece.Piece;

/**
 * This class pertains to a non-attacking move.
 */
public final class MajorMove extends Move {

    /**
     * A constructor for a MajorMove (non-attacking move) object.
     * @param board the chess board
     * @param movedPiece the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
     */
    public MajorMove
            (final Board board,
             final Piece movedPiece,
             final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    /**
     * @return the new board with the executed MajorMove
     */
    @Override
    public Board executeMove() {
        return null;
    }
}
