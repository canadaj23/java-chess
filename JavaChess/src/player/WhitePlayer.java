package player;

import board.Board;
import move.Move;
import piece.attributes.Alliance;
import piece.Piece;

import java.util.Collection;

import static piece.attributes.Alliance.WHITE;

public class WhitePlayer extends Player {

    /**
     * Constructor for a WhitePlayer object.
     * @param board the chess board
     * @param whiteInitialLegalMoves white player's starting legal moves
     * @param blackInitialLegalMoves black player's starting legal moves
     */
    public WhitePlayer(
            final Board board,
            final Collection<Move> whiteInitialLegalMoves,
            final Collection<Move> blackInitialLegalMoves) {
        super(board, whiteInitialLegalMoves, blackInitialLegalMoves);
    }

    /**
     * @return the white pieces
     */
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    /**
     * @return the white player's alliance
     */
    @Override
    public Alliance getAlliance() {
        return WHITE;
    }

    /**
     * @return the white player's opponent (black)
     */
    @Override
    public Player getOpponent() {
        return this.board.getOpponent(WHITE);
    }
}
