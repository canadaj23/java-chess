package chess.game.move.castle;

import chess.game.board.Board;
import chess.game.piece.Piece;
import chess.game.piece.pieces.Rook;

public final class KingSideCastleMove extends CastleMove {

    /**
     * Constructor for a KingSideCastleMove object.
     * @param board                 the chess chess.game.board
     * @param movedPiece            the chess.game.piece that wants to chess.game.move
     * @param destinationCoordinate the destination's coordinate on the chess.game.board
     */
    public KingSideCastleMove(
            final Board board,
            final Piece movedPiece,
            final int destinationCoordinate,
            final Rook castleRook) {
        super(board, movedPiece, destinationCoordinate, castleRook);
    }

    /**
     * @return the String form of a King-side castle in PGN format
     */
    @Override
    public String toString() {
        return "O-O";
    }
}
