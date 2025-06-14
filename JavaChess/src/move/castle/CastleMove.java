package move.castle;

import board.Board;
import move.Move;
import piece.Piece;
import piece.pieces.Rook;

import static piece.attributes.Alliance.BLACK;

/**
 * This class pertains to castling.
 */
public abstract class CastleMove extends Move {
    protected final Rook castleRook;
    protected final int castleRookStart;
    protected int castleRookDestination;

    /**
     * Constructor for a CastleMove object.
     * @param board                 the chess board
     * @param movedPiece            the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
     */
    public CastleMove(
            final Board board,
            final Piece movedPiece,
            final int destinationCoordinate,
            final Rook castleRook) {
        super(board, movedPiece, destinationCoordinate);
        this.castleRook = castleRook;
        castleRookStart = castleRook.getPiecePosition();
        castleRookDestination = castleRook.getPieceAlliance() == BLACK ?
                (castleRookStart == 0 ? castleRookStart + 3 : castleRookStart - 2) :
                (castleRookStart == 63 ? castleRookStart - 2 : castleRookStart + 3);
    }

    /**
     * @return the castling Rook
     */
    public Rook getCastleRook() {
        return this.castleRook;
    }

    /**
     * @return that it is a castling move
     */
    @Override
    public boolean isCastlingMove() {
        return true;
    }

    /**
     * @return a new board with the castling move
     */
    @Override
    public Board executeMove() {
        final Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
        // Make the current player's active pieces
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
            if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
                boardBuilder.setPiece(piece);
            }
        }
        // Make the opponent's active pieces
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
            if (!this.movedPiece.equals(piece)) {
                boardBuilder.setPiece(piece);
            }
        }

        boardBuilder.setPiece(this.movedPiece.movePiece(this));
        boardBuilder.setPiece(new Rook(this.castleRookDestination, this.castleRook.getPieceAlliance()));
        boardBuilder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());

        return boardBuilder.buildBoard();
    }
}
