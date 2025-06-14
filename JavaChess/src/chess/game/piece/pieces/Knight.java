package chess.game.piece.pieces;

import chess.game.board.Board;
import chess.game.move.other.AttackMove;
import chess.game.move.other.MajorMove;
import chess.game.move.Move;
import chess.game.piece.Piece;
import chess.game.piece.attributes.Alliance;
import chess.game.tile.Tile;

import java.util.*;

import static chess.game.piece.attributes.PieceType.KNIGHT;
import static chess.game.utils.Constants.PieceConstants.KNIGHT_CANDIDATE_MOVE_COORDS;
import static chess.game.utils.HelperMethods.IsValidTileCoordinate;

/**
 * This is the class for the Knight chess chess.game.piece.
 */
public class Knight extends Piece {

    /**
     * Constructor for a Knight.
     * @param piecePosition the position of the Knight
     * @param pieceAlliance black/white
     */
    public Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(KNIGHT, piecePosition, pieceAlliance);
    }

    /**
     * Calculates the moves of the Knight.
     * @param board the chess.game.board holding the Knight
     * @return a list of legal moves for the Knight
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        // Iterate through all offsets for the Knight to determine legal moves
        for (final int currentCandidateOffset : KNIGHT_CANDIDATE_MOVE_COORDS) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            // Determine if the Knight is within the bounds of the chess.game.board
            if (IsValidTileCoordinate(candidateDestinationCoordinate)) {
                // Determine if the Knight is on the 1st, 2nd, 7th, or 8th columns
                if (isKnightColumnExcluded(candidateDestinationCoordinate)) {
                    continue;
                }
                // Obtain the destination chess.game.tile with the new coordinate
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                // Determine what chess.game.move the Knight is trying to do
                determineKnightMove(candidateDestinationTile, legalMoves, board, candidateDestinationCoordinate);
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * @param move what the chess.game.piece is doing
     * @return a moved chess.game.piece
     */
    @Override
    public Knight movePiece(final Move move) {
        return new Knight(this.piecePosition, move.getMovedPiece().getPieceAlliance());
    }

    /**
     * Determines if the Knight is on the 1st, 2nd, 7th, or 8th columns.
     * @param candidateDestinationCoordinate where the Knight wants to chess.game.move to
     * @return if the Knight is on the 1st, 2nd, 7th, or 8th columns
     */
    private boolean isKnightColumnExcluded(final int candidateDestinationCoordinate) {
        int column = (candidateDestinationCoordinate % 8) + 1;
        return column == 1 || column == 2 || column == 7 || column == 8;
    }

    /**
     * Determines if the Knight's chess.game.move is a MajorMove or an AttackMove.
     * @param candidateDestinationTile the chess.game.tile the Knight wants to chess.game.move to
     * @param legalMoves the list of legal moves of the Knight to be added to
     * @param board the chess.game.board that holds the Knight
     * @param candidateDestinationCoord the coordinate (chess.game.tile number) of the destination chess.game.tile
     */
    private void determineKnightMove(
            final Tile candidateDestinationTile,
            final List<Move> legalMoves,
            final Board board,
            final int candidateDestinationCoord) {
        if (!candidateDestinationTile.isTileOccupied()) {
            // An empty chess.game.tile makes the chess.game.move a MajorMove
            legalMoves.add(
                    new MajorMove(board,
                            this,
                            candidateDestinationCoord));
        } else {
            // Determine what the chess.game.piece and its alliance are
            final Piece pieceAtLocation = candidateDestinationTile.getPieceOnTile();
            final Alliance pieceAlliance = pieceAtLocation.getPieceAlliance();

            if (this.pieceAlliance != pieceAlliance) {
                // The chess.game.move is an AttackMove
                legalMoves.add(
                        new AttackMove(board,
                                this,
                                candidateDestinationCoord,
                                pieceAtLocation));
            }
        }
    }
}
