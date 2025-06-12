package piece;

import board.Board;
import move.AttackMove;
import move.MajorMove;
import move.Move;
import tile.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static utils.Constants.PieceConstants.BISHOP_CANDIDATE_MOVE_VECTOR_COORDS;
import static utils.HelperMethods.IsValidTileCoordinate;

/**
 * This is the class for the Bishop chess piece.
 */
public class Bishop extends Piece {
    /**
     * Constructor for the Bishop.
     *
     * @param piecePosition the position of the Bishop
     * @param pieceAlliance black/white
     */
    protected Bishop(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    /**
     * Calculates the moves of the Bishop.
     *
     * @param board the board holding the piece
     * @return a list of legal moves for the Bishop
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        // Iterate through all offsets for the Bishop to determine legal moves
        for (final int currentCandidateOffset : BISHOP_CANDIDATE_MOVE_VECTOR_COORDS) {
            // Increase candidateDestinationCoord with the offset
            int candidateDestinationCoord = this.piecePosition + currentCandidateOffset;

            // Continue while the Bishop is within the bounds of the board
            while (IsValidTileCoordinate(candidateDestinationCoord)) {
                // Determine if the Bishop on the 1st or 8th columns
                if (isBishopColumnExcluded(candidateDestinationCoord)) {
                    break;
                }
                // Obtain the destination tile with the new coordinate
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoord);
                // Determine what move the Bishop is trying to do
                if (!candidateDestinationTile.isTileOccupied()) {
                    // An empty tile makes the move a MajorMove
                    legalMoves.add(
                            new MajorMove(board,
                                    this,
                                    candidateDestinationCoord));
                } else {
                    // Determine what the piece and its alliance are
                    final Piece pieceAtLocation = candidateDestinationTile.getPieceOnTile();
                    final Alliance pieceAlliance = pieceAtLocation.getPieceAlliance();

                    if (this.pieceAlliance != pieceAlliance) {
                        // The move is an AttackMove
                        legalMoves.add(new AttackMove(
                                board,
                                this,
                                candidateDestinationCoord,
                                pieceAtLocation));
                    }
                    // There is no need to continue using the current offset if a piece is in the way
                    break;
                }
                candidateDestinationCoord += currentCandidateOffset;
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * Determines if the column the Bishop is on the 1st or 8th columns.
     * @param candidateDestinationCoordinate where the Bishop wants to move to
     * @return if the Bishop is on the 1st or 8th columns
     */
    private boolean isBishopColumnExcluded(final int candidateDestinationCoordinate) {
        int column = (candidateDestinationCoordinate % 8) + 1;
        return column == 1 || column == 8;
    }
}
