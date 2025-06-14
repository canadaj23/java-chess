package move.pawn;

import board.Board;
import move.Move;
import piece.Piece;

/**
 * This class pertains to the jumping of a pawn.
 */
public final class PawnJumpMove extends Move {

    /**
     * Constructor for a PawnJumpMove object.
     * @param board                 the chess board
     * @param movedPiece            the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
     */
    public PawnJumpMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
