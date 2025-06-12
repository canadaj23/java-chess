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

import static utils.Constants.PieceConstants.KNIGHT_CANDIDATE_MOVE_COORDS;
import static utils.HelperMethods.IsValidTileCoordinate;

/**
 * This is the class for the Knight chess piece.
 */
public class Knight extends Piece {

    /**
     * Constructor for a Knight.
     *
     * @param piecePosition the position of the Knight
     * @param pieceAlliance black/white
     */
    protected Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    /**
     * Calculates the moves of the Knight.
     *
     * @param board the board holding the Knight
     * @return a list of legal moves for the Knight
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        // Iterate through all offsets for the Knight to determine legal moves
        for (final int currentCandidateOffset : KNIGHT_CANDIDATE_MOVE_COORDS) {
            int candidateDestinationCoord = this.piecePosition + currentCandidateOffset;

            // Determine if the Knight is within the bounds of the board
            if (IsValidTileCoordinate(candidateDestinationCoord)) {
                // Determine if the Knight is on the 1st, 2nd, 7th, or 8th columns
                if (isKnightColumnExcluded(candidateDestinationCoord)) {
                    continue;
                }
                // Obtain the destination tile with the new coordinate
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoord);
                // Determine what move the Knight is trying to do
                determineKnightMove(candidateDestinationTile, legalMoves, board, candidateDestinationCoord);
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * Determines if the Knight is on the 1st, 2nd, 7th, or 8th columns.
     * @param candidateDestinationCoordinate where the Knight wants to move to
     * @return if the Knight is on the 1st, 2nd, 7th, or 8th columns
     */
    private boolean isKnightColumnExcluded(final int candidateDestinationCoordinate) {
        int column = (candidateDestinationCoordinate % 8) + 1;
        return column == 1 || column == 2 || column == 7 || column == 8;
    }

    /**
     * Determines if the Knight's move is a MajorMove or an AttackMove.
     * @param candidateDestinationTile the tile the Knight wants to move to
     * @param legalMoves the list of legal moves of the Knight to be added to
     * @param board the board that holds the Knight
     * @param candidateDestinationCoord the coordinate (tile number) of the destination tile
     */
    private void determineKnightMove(
            final Tile candidateDestinationTile,
            final List<Move> legalMoves,
            final Board board,
            final int candidateDestinationCoord) {
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
                legalMoves.add(
                        new AttackMove(board,
                                this,
                                candidateDestinationCoord,
                                pieceAtLocation));
            }
        }
    }
}
