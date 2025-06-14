package chess.game.move.pawn;

import chess.game.board.Board;
import chess.game.move.Move;
import chess.game.piece.Piece;
import chess.game.piece.pieces.Pawn;

/**
 * This class pertains to the jumping of a pawn.
 */
public final class PawnJump extends Move {

    /**
     * Constructor for a PawnJump object.
     * @param board                 the chess chess.game.board
     * @param movedPiece            the chess.game.piece that wants to chess.game.move
     * @param destinationCoordinate the destination's coordinate on the chess.game.board
     */
    public PawnJump(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    /**
     * @return a new chess.game.board with the moved pawn
     */
    @Override
    public Board executeMove() {
        final Board.BoardBuilder boardBuilder = new Board.BoardBuilder();

        makePieces(boardBuilder);

        final Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this);

        boardBuilder.setPiece(movedPawn);
        boardBuilder.setEnPassantPawn(movedPawn);
        boardBuilder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());

        // The Pawn will either be null or an En Passant Pawn
        return boardBuilder.buildBoard();
    }
}
