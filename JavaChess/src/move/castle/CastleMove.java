package move.castle;

import board.Board;
import move.Move;
import piece.Piece;

/**
 * This class pertains to castling.
 */
public abstract class CastleMove extends Move {

    /**
     * Constructor for a CastleMove object.
     * @param board                 the chess board
     * @param movedPiece            the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
     */
    public CastleMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
