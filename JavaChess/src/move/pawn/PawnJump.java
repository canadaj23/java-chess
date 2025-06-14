package move.pawn;

import board.Board;
import move.Move;
import piece.Piece;
import piece.pieces.Pawn;

/**
 * This class pertains to the jumping of a pawn.
 */
public final class PawnJump extends Move {

    /**
     * Constructor for a PawnJump object.
     * @param board                 the chess board
     * @param movedPiece            the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
     */
    public PawnJump(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        super(board, movedPiece, destinationCoordinate);
    }

    /**
     * @return a new board with the moved pawn
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
