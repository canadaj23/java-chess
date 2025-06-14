package move.pawn;

import board.Board;
import move.Move;
import piece.Piece;

/**
 * This class pertains to a normal pawn move.
 */
public final class PawnMove extends Move {

    /**
     * Constructor for a PawnMove object.
     * @param board                 the chess board
     * @param movedPiece            the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
     */
    public PawnMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
