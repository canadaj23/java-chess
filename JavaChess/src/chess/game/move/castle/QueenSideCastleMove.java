package chess.game.move.castle;

import chess.game.board.Board;
import chess.game.piece.Piece;
import chess.game.piece.pieces.Rook;

public final class QueenSideCastleMove extends CastleMove {

    /**
     * Constructor for a QueenSideCastleMove object.
     * @param board                 the chess chess.game.board
     * @param movedPiece            the chess.game.piece that wants to chess.game.move
     * @param destinationCoordinate the destination's coordinate on the chess.game.board
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
