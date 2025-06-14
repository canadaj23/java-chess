package chess.game.move;

import chess.game.board.Board;
import chess.game.move.other.NullMove;
import chess.game.piece.Piece;

/**
 * This class serves as a blueprint for any type of chess chess.game.move.
 */
public abstract class Move {
    protected final Board board;
    protected final Piece movedPiece;
    protected final int destinationCoordinate;
    public static final Move NULL_MOVE = new NullMove();

    /**
     * Constructor for a Move object.
     * This will not be allowed to become an object on its own, just its subclasses.
     * @param board the chess chess.game.board
     * @param movedPiece the chess.game.piece that wants to chess.game.move
     * @param destinationCoordinate the destination's coordinate on the chess.game.board
     */
    protected Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    /**
     * @return the coordinate of the destination chess.game.tile
     */
    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    /**
     * @return the chess.game.piece to be moved
     */
    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    /**
     * @return the current location of the chess.game.piece
     */
    public int getCurrentCoordinate() {
        return this.movedPiece.getPiecePosition();
    }

    /**
     * A subsequent chess.game.board is made when a chess.game.move is performed.
     * @return the new chess.game.board with the executed MajorMove
     */
    public Board executeMove() {
        final Board.BoardBuilder boardBuilder = new Board.BoardBuilder();

        makePieces(boardBuilder);

        boardBuilder.setPiece(this.movedPiece.movePiece(this));
        boardBuilder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());

        return boardBuilder.buildBoard();
    }

    /**
     * @param boardBuilder what will make the subsequent chess.game.board
     */
    protected void makePieces(final Board.BoardBuilder boardBuilder) {
        // Make the current chess.game.player's active pieces
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
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

    /**
     * @return whether the chess.game.move is an attack
     */
    public boolean isAttack() {
        return false;
    }

    /**
     * @return whether the chess.game.move is a castling chess.game.move
     */
    public boolean isCastlingMove() {
        return false;
    }

    /**
     * @return the attacked chess.game.piece
     */
    public Piece getAttackedPiece() {
        return null;
    }

    /**
     * @return an overridden hashcode
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + this.destinationCoordinate;
        result = 31 * result + this.movedPiece.hashCode();

        return result;
    }

    /**
     * Determines if two moves are the same.
     * @param other the other chess.game.move in question
     * @return whether two moves are the same
     */
    @Override
    public boolean equals(final Object other) {
        // If the moves are equal
        if (this == other) {
            return true;
        }
        // If the other chess.game.move is not a Move
        if (!(other instanceof Move)) {
            return false;
        }

        final Move otherMove = (Move) other;

        return getDestinationCoordinate() == otherMove.getDestinationCoordinate() &&
               getMovedPiece().equals(otherMove.getMovedPiece());
    }
}
