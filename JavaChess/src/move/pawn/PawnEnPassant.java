package move.pawn;

import board.Board;
import piece.Piece;

/**
 * This class pertains to the En Passant move.
 */
public final class PawnEnPassant extends PawnAttackMove {

    /**
     * Constructor for a PawnEnPassantMove object.
     * @param board                 the chess board
     * @param movedPiece            the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
     * @param attackedPiece         the piece that is getting attacked
     */
    public PawnEnPassant(final Board board,
                         final Piece movedPiece,
                         final int destinationCoordinate,
                         final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate, attackedPiece);
    }
}
