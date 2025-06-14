package piece.pieces;

import board.Board;
import move.Move;
import piece.Piece;
import piece.attributes.Alliance;

import java.util.*;

import static piece.attributes.PieceType.BISHOP;
import static utils.Constants.PieceConstants.BISHOP_CANDIDATE_MOVE_VECTOR_COORDS;
import static utils.HelperMethods.*;

/**
 * This is the class for the Bishop chess piece.
 */
public class Bishop extends Piece {

    /**
     * Constructor for the Bishop.
     * @param piecePosition the position of the Bishop
     * @param pieceAlliance black/white
     */
    public Bishop(int piecePosition, Alliance pieceAlliance) {
        super(BISHOP, piecePosition, pieceAlliance);
    }

    /**
     * Calculates the moves of the Bishop.
     * @param board the board holding the piece
     * @return a list of legal moves for the Bishop
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        // Iterate through all offsets for the Bishop to determine legal moves
        for (final int currentCandidateOffset : BISHOP_CANDIDATE_MOVE_VECTOR_COORDS) {
            // Determine the first destination coordinate
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            // Find the Bishop's legal moves
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
    public Bishop movePiece(final Move move) {
        return new Bishop(this.piecePosition, move.getMovedPiece().getPieceAlliance());
    }
}
