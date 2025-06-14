package chess.game.piece.pieces;

import chess.game.board.Board;
import chess.game.move.Move;
import chess.game.piece.Piece;
import chess.game.piece.attributes.Alliance;

import java.util.*;

import static chess.game.piece.attributes.PieceType.QUEEN;
import static chess.game.utils.Constants.PieceConstants.QUEEN_OR_KING_CANDIDATE_MOVE_VECTOR_COORDS;
import static chess.game.utils.HelperMethods.FindSlidingPieceLegalMoves;

/**
 * This is the class for the Queen chess chess.game.piece.
 */
public class Queen extends Piece {

    /**
     * Constructor for the Queen.
     * @param piecePosition the position of the Queen
     * @param pieceAlliance black/white
     */
    public Queen(final int piecePosition, final Alliance pieceAlliance) {
        super(QUEEN, piecePosition, pieceAlliance);
    }

    /**
     * Calculates the moves of the Queen.
     * @param board the chess.game.board holding the Queen
     * @return a list of legal moves for the Queen
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        // Iterate through all offsets for the Queen to determine legal moves
        for (final int currentCandidateOffset : QUEEN_OR_KING_CANDIDATE_MOVE_VECTOR_COORDS) {
            // Determine the first destination coordinate
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            // Find the Queen's legal moves
            FindSlidingPieceLegalMoves(
                    candidateDestinationCoordinate,
                    this,
                    board,
                    legalMoves,
                    currentCandidateOffset);
        }

        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * @param move what the chess.game.piece is doing
     * @return a moved chess.game.piece
     */
    @Override
    public Queen movePiece(final Move move) {
        return new Queen(this.piecePosition, move.getMovedPiece().getPieceAlliance());
    }
}
