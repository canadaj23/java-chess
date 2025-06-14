package piece.pieces;

import board.Board;
import move.Move;
import piece.Piece;
import piece.attributes.Alliance;

import java.util.*;

import static piece.attributes.PieceType.ROOK;
import static utils.Constants.PieceConstants.ROOK_CANDIDATE_MOVE_VECTOR_COORDS;
import static utils.HelperMethods.*;

/**
 * This is the class for the Rook chess piece.
 */
public class Rook extends Piece {

    /**
     * Constructor for a Rook.
     * @param piecePosition the position of the Rook
     * @param pieceAlliance black/white
     */
    public Rook(final int piecePosition, final Alliance pieceAlliance) {
        super(ROOK, piecePosition, pieceAlliance);
    }

    /**
     * Calculates the moves of the Rook.
     * @param board the board holding the Rook
     * @return a list of legal moves for the Rook
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        // Iterate through all offsets for the Rook to determine legal moves
        for (final int currentCandidateOffset : ROOK_CANDIDATE_MOVE_VECTOR_COORDS) {
            // Determine the first destination coordinate
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            // Find the Rook's legal moves
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
    public Rook movePiece(final Move move) {
        return new Rook(this.piecePosition, move.getMovedPiece().getPieceAlliance());
    }
}
