package chess.game.piece.pieces;

import chess.game.board.Board;
import chess.game.move.Move;
import chess.game.piece.Piece;
import chess.game.piece.attributes.Alliance;

import java.util.*;

import static chess.game.piece.attributes.PieceType.BISHOP;
import static chess.game.utils.Constants.PieceConstants.BISHOP_CANDIDATE_MOVE_VECTOR_COORDS;
import static chess.game.utils.HelperMethods.*;

/**
 * This is the class for the Bishop chess chess.game.piece.
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
     * @param board the chess.game.board holding the chess.game.piece
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
     * @param move what the chess.game.piece is doing
     * @return a moved chess.game.piece
     */
    @Override
    public Bishop movePiece(final Move move) {
        return new Bishop(this.piecePosition, move.getMovedPiece().getPieceAlliance());
    }
}
