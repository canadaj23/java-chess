package move;

import board.Board;
import move.other.NullMove;
import piece.Piece;

/**
 * This class serves as a blueprint for any type of chess move.
 */
public abstract class Move {
    protected final Board board;
    protected final Piece movedPiece;
    protected final int destinationCoordinate;
    public static final Move NULL_MOVE = new NullMove();

    /**
     * Constructor for a Move object.
     * This will not be allowed to become an object on its own, just its subclasses.
     * @param board the chess board
     * @param movedPiece the piece that wants to move
     * @param destinationCoordinate the destination's coordinate on the board
     */
    protected Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    /**
     * @return the coordinate of the destination tile
     */
    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    /**
     * @return the piece to be moved
     */
    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    /**
     * @return the current location of the piece
     */
    public int getCurrentCoordinate() {
        return this.movedPiece.getPiecePosition();
    }

    /**
     * A subsequent board is made when a move is performed.
     * @return the new board with the executed MajorMove
     */
    public Board executeMove() {
        final Board.BoardBuilder boardBuilder = new Board.BoardBuilder();

        makePieces(boardBuilder);

        boardBuilder.setPiece(this.movedPiece.movePiece(this));
        boardBuilder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());

        return boardBuilder.buildBoard();
    }

    /**
     * @param boardBuilder what will make the subsequent board
     */
    private void makePieces(final Board.BoardBuilder boardBuilder) {
        // Make the current player's active pieces
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
            // TODO: implement hashcode and equals for pieces
            if (!this.movedPiece.equals(piece)) {
                boardBuilder.setPiece(piece);
            }
        }

        // Make the opponent's active pieces
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
            if (!this.movedPiece.equals(piece)) {
                boardBuilder.setPiece(piece);
            }
        }
    }
}
