package move.castle;

import board.Board;
import piece.Piece;
import piece.pieces.Rook;

public final class KingSideCastleMove extends CastleMove {

    /**
     * Constructor for a KingSideCastleMove object.
     * @param board                 the chess board
     * @param movedPiece            the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
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
