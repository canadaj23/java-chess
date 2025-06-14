package move.castle;

import board.Board;
import piece.Piece;
import piece.pieces.Rook;

public final class QueenSideCastleMove extends CastleMove {

    /**
     * Constructor for a QueenSideCastleMove object.
     * @param board                 the chess board
     * @param movedPiece            the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
     */
    public QueenSideCastleMove(Board board, Piece movedPiece, int destinationCoordinate, Rook castleRook) {
        super(board, movedPiece, destinationCoordinate, castleRook);
    }

    /**
     * @return the String form of a Queen-side castle in PGN format
     */
    @Override
    public String toString() {
        return "O-O-O";
    }
}
