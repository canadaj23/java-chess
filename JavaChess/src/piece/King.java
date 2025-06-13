package piece;

import board.Board;
import move.*;
import tile.Tile;

import java.util.*;

import static utils.Constants.PieceConstants.QUEEN_OR_KING_CANDIDATE_MOVE_VECTOR_COORDS;
import static utils.HelperMethods.*;

/**
 * This is the class for the King chess piece.
 */
public class King extends Piece {

    /**
     * Constructor for the King.
     *
     * @param piecePosition the position of the King
     * @param pieceAlliance black/white
     */
    public King(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
        pieceName = getPieceAlliance().isBlack() ? "k" : "K";
        isKing = true;
    }

    /**
     * Calculates the moves of the King.
     *
     * @param board the board holding the King
     * @return a list of legal moves for the King
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        // Iterate through all offsets for the King to determine legal moves
        for (final int currentCandidateOffset : QUEEN_OR_KING_CANDIDATE_MOVE_VECTOR_COORDS) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            // Determine if the King is in the 1st or 8th column
            if  (IsSlidingPieceColumnExcluded(candidateDestinationCoordinate)) {
                continue;
            }

            // Decide if the King can perform a move
            if (IsValidTileCoordinate(candidateDestinationCoordinate)) {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                // Determine what move the Knight is trying to do
                determineKingMove(candidateDestinationTile, legalMoves, board, candidateDestinationCoordinate);
            }
        }

        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * Determines if the King's move is a MajorMove or an AttackMove.
     * @param candidateDestinationTile the tile the King wants to move to
     * @param legalMoves the list of legal moves of the King to be added to
     * @param board the board that holds the King
     * @param candidateDestinationCoord the coordinate (tile number) of the destination tile
     */
    private void determineKingMove(
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
        } else { // An occupied tile means a possible ally or opponent piece
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
