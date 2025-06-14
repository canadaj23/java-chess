package chess.game.piece.pieces;

import chess.game.board.Board;
import chess.game.move.*;
import chess.game.move.other.AttackMove;
import chess.game.move.other.MajorMove;
import chess.game.piece.Piece;
import chess.game.piece.attributes.Alliance;
import chess.game.tile.Tile;

import java.util.*;

import static chess.game.piece.attributes.PieceType.KING;
import static chess.game.utils.Constants.PieceConstants.QUEEN_OR_KING_CANDIDATE_MOVE_VECTOR_COORDS;
import static chess.game.utils.HelperMethods.*;

/**
 * This is the class for the King chess chess.game.piece.
 */
public class King extends Piece {

    /**
     * Constructor for the King.
     *
     * @param piecePosition the position of the King
     * @param pieceAlliance black/white
     */
    public King(final int piecePosition, final Alliance pieceAlliance) {
        super(KING, piecePosition, pieceAlliance);
        isKing = true;
    }

    /**
     * Calculates the moves of the King.
     *
     * @param board the chess.game.board holding the King
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

            // Decide if the King can perform a chess.game.move
            if (IsValidTileCoordinate(candidateDestinationCoordinate)) {
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                // Determine what chess.game.move the Knight is trying to do
                determineKingMove(candidateDestinationTile, legalMoves, board, candidateDestinationCoordinate);
            }
        }

        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * @param move what the chess.game.piece is doing
     * @return a moved chess.game.piece
     */
    @Override
    public King movePiece(final Move move) {
        return new King(this.piecePosition, move.getMovedPiece().getPieceAlliance());
    }

    /**
     * Determines if the King's chess.game.move is a MajorMove or an AttackMove.
     * @param candidateDestinationTile the chess.game.tile the King wants to chess.game.move to
     * @param legalMoves the list of legal moves of the King to be added to
     * @param board the chess.game.board that holds the King
     * @param candidateDestinationCoord the coordinate (chess.game.tile number) of the destination chess.game.tile
     */
    private void determineKingMove(
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
        } else { // An occupied chess.game.tile means a possible ally or opponent chess.game.piece
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
