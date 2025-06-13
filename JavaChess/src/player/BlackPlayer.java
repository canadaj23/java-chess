package player;

import board.Board;
import move.Move;
import piece.Alliance;
import piece.Piece;

import java.util.Collection;

import static piece.Alliance.BLACK;

public class BlackPlayer extends Player {

    /**
     * Constructor for a BlackPlayer object.
     * @param board the chess board
     * @param whiteInitialLegalMoves white player's starting legal moves
     * @param blackInitialLegalMoves black player's starting legal moves
     */
    public BlackPlayer(
            final Board board,
            final Collection<Move> whiteInitialLegalMoves,
            final Collection<Move> blackInitialLegalMoves) {
        super(board, blackInitialLegalMoves, whiteInitialLegalMoves);
    }

    /**
     * @return the black pieces
     */
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    /**
     * @return the black player's alliance
     */
    @Override
    public Alliance getAlliance() {
        return BLACK;
    }

    /**
     * @return the black player's opponent (white)
     */
    @Override
    public Player getOpponent() {
        return this.board.getOpponent(BLACK);
    }

}
