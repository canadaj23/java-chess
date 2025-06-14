package move.pawn;

import board.Board;
import move.other.AttackMove;
import piece.Piece;

/**
 * This class pertains to an attacking pawn move.
 */
public class PawnAttackMove extends AttackMove {

    /**
     * Constructor for a PawnAttackMove object.
     * @param board                 the chess board
     * @param movedPiece            the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
     * @param attackedPiece         the piece that is getting attacked
     */
    public PawnAttackMove(final Board board,
                          final Piece movedPiece,
                          final int destinationCoordinate,
                          final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate, attackedPiece);
    }
}
