package piece.pieces;

import board.Board;
import move.Move;
import piece.Piece;
import piece.attributes.Alliance;

import java.util.*;

import static piece.attributes.PieceType.QUEEN;
import static utils.Constants.PieceConstants.QUEEN_OR_KING_CANDIDATE_MOVE_VECTOR_COORDS;
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
        super(QUEEN, piecePosition, pieceAlliance);
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
     * @param move what the piece is doing
     * @return a moved piece
     */
    @Override
    public Queen movePiece(final Move move) {
        return new Queen(this.piecePosition, move.getMovedPiece().getPieceAlliance());
    }
}
