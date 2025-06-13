package move;

import board.Board;
import piece.Piece;

/**
 * This class pertains to attacking moves.
 */
public final class AttackMove extends Move {
    private final Piece attackedPiece;

    /**
     * A constructor for an AttackMove object.
     * @param board the chess board
     * @param movedPiece the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
     * @param attackedPiece the piece that is getting attacked
     */
    public AttackMove
            (final Board board,
             final Piece movedPiece,
             final int destinationCoordinate,
             final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.attackedPiece = attackedPiece;
    }

    /**
     * @return the new board with the executed AttackMove
     */
    @Override
    public Board executeMove() {
        return null;
    }
}
