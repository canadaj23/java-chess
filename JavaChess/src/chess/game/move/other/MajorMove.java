package chess.game.move.other;

import chess.game.board.Board;
import chess.game.move.Move;
import chess.game.piece.Piece;

/**
 * This class pertains to a non-attacking chess.game.move.
 */
public class MajorMove extends Move {

    /**
     * Constructor for a MajorMove (non-attacking chess.game.move) object.
     * @param board the chess chess.game.board
     * @param movedPiece the chess.game.piece that wants to chess.game.move
     * @param destinationCoordinate the destination's coordinate on the chess.game.board
     */
    public MajorMove
            (final Board board,
             final Piece movedPiece,
             final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }
}
