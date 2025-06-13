package move;

import board.Board;
import piece.Piece;

/**
 * This class serves as a blueprint for any type of chess move.
 */
public abstract class Move {
    protected final Board board;
    protected final Piece movedPiece;
    protected final int destinationCoordinate;

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
     * @return the new board with the executed move
     */
    public abstract Board executeMove();
}
