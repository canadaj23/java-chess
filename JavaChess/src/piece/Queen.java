package piece;

import board.Board;
import move.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static utils.Constants.PieceConstants.QUEEN_CANDIDATE_MOVE_VECTOR_COORDS;
import static utils.HelperMethods.FindSlidingPieceLegalMoves;

/**
 * This is the class for the Queen chess piece.
 */
public class Queen extends Piece {

    /**
     * Constructor for the Queen.
     * @param piecePosition the position of the Queen
     * @param pieceAlliance black/white
     */
    public Queen(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    /**
     * Calculates the moves of the Queen.
     * @param board the board holding the Queen
     * @return a list of legal moves for the Queen
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        // Iterate through all offsets for the Queen to determine legal moves
        for (final int currentCandidateOffset : QUEEN_CANDIDATE_MOVE_VECTOR_COORDS) {
            // Determine the first destination coordinate
            int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

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
}
