package piece;

import board.Board;
import move.MajorMove;
import move.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static utils.Constants.PieceConstants.PAWN_CANDIDATE_MOVE_COORD;
import static utils.HelperMethods.IsValidTileCoordinate;

/**
 * This is the class for the Pawn chess piece.
 */
public class Pawn extends Piece {

    /**
     * Constructor for the Pawn.
     *
     * @param piecePosition the position of the Pawn
     * @param pieceAlliance black/white
     */
    protected Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    /**
     * Calculates the moves of the Pawn.
     *
     * @param board the board holding the Pawn
     * @return a list of legal moves for the Pawn
     */
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        // Iterate through all offsets for the Pawn to determine legal moves
        for (final int currentCandidateOffset : PAWN_CANDIDATE_MOVE_COORD) {
            // Determine the first destination coordinate
            int candidateDestinationCoordinate =
                    this.piecePosition +
                    (getPieceAlliance().getDirection() * currentCandidateOffset);
            // If the pawn is at the bottom or top row, it cannot progress more
            if (!IsValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            // Progress if the tile is forward by one and the destination tile is empty
            if (currentCandidateOffset == 8 && !(board.getTile(candidateDestinationCoordinate).isTileOccupied())) {
                // TODO: implement how a pawn should actually move
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            }

        }

        return legalMoves;
    }
}
