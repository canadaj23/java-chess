package chess.game.move.pawn;

import chess.game.board.Board;
import chess.game.piece.Piece;

/**
 * This class pertains to the En Passant chess.game.move.
 */
public final class PawnEnPassant extends PawnAttackMove {

    /**
     * Constructor for a PawnEnPassantMove object.
     * @param board                 the chess chess.game.board
     * @param movedPiece            the chess.game.piece that wants to chess.game.move
     * @param destinationCoordinate the destination's coordinate on the chess.game.board
     * @param attackedPiece         the chess.game.piece that is getting attacked
     */
    public PawnEnPassant(final Board board,
                         final Piece movedPiece,
                         final int destinationCoordinate,
                         final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate, attackedPiece);
    }
}
