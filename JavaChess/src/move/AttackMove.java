package move;

import board.Board;
import piece.Piece;

public final class AttackMove extends Move {
    private final Piece attackedPiece;

    public AttackMove
            (final Board board,
             final Piece movedPiece,
             final int destinationCoordinate,
             final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.attackedPiece = attackedPiece;
    }
}
