package chess.game.move.pawn;

import chess.game.board.Board;
import chess.game.move.Move;
import chess.game.piece.Piece;

/**
 * This class pertains to a normal pawn chess.game.move.
 */
public final class PawnMove extends Move {

    /**
     * Constructor for a PawnMove object.
     * @param board                 the chess chess.game.board
     * @param movedPiece            the chess.game.piece that wants to chess.game.move
     * @param destinationCoordinate the destination's coordinate on the chess.game.board
     */
    public PawnMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
