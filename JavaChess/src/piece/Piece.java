package piece;

import board.Board;
import move.Move;

import java.util.Collection;

/**
 * This class serves as a blueprint for various chess pieces.
 */
public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;

    /**
     * Constructor for a Piece.
     * @param piecePosition the position of the piece
     * @param pieceAlliance black/white
     */
    protected Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }

    /**
     * Calculates the moves of a given piece.
     * @param board the board holding the piece
     * @return a list of legal moves for a given piece
     */
    public abstract Collection<Move> calculateLegalMoves(final Board board);

    /**
     * Determines the alliance of the piece.
     * @return black/white
     */
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
}
