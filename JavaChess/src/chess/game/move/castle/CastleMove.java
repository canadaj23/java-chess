package chess.game.move.castle;

import chess.game.board.Board;
import chess.game.move.Move;
import chess.game.piece.Piece;
import chess.game.piece.pieces.Rook;

import static chess.game.piece.attributes.Alliance.BLACK;

/**
 * This class pertains to castling.
 */
public abstract class CastleMove extends Move {
    protected final Rook castleRook;
    protected final int castleRookStart;
    protected int castleRookDestination;

    /**
     * Constructor for a CastleMove object.
     * @param board                 the chess chess.game.board
     * @param movedPiece            the chess.game.piece that wants to chess.game.move
     * @param destinationCoordinate the destination's coordinate on the chess.game.board
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
     * @return that it is a castling chess.game.move
     */
    @Override
    public boolean isCastlingMove() {
        return true;
    }

    /**
     * @return a new chess.game.board with the castling chess.game.move
     */
    @Override
    public Board executeMove() {
        final Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
        // Make the current chess.game.player's active pieces
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
