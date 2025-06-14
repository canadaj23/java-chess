package chess.game.piece.pieces;

import chess.game.board.Board;
import chess.game.move.other.MajorMove;
import chess.game.move.Move;
import chess.game.piece.Piece;
import chess.game.piece.attributes.Alliance;

import java.util.*;

import static chess.game.piece.attributes.PieceType.PAWN;
import static chess.game.utils.Constants.PieceConstants.PAWN_CANDIDATE_MOVE_COORD;
import static chess.game.utils.HelperMethods.*;

/**
 * This is the class for the Pawn chess chess.game.piece.
 */
public class Pawn extends Piece {

    /**
     * Constructor for the Pawn.
     *
     * @param piecePosition the position of the Pawn
     * @param pieceAlliance black/white
     */
    public Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(PAWN, piecePosition, pieceAlliance);
    }

    /**
     * Calculates the moves of the Pawn.
     *
     * @param board the chess.game.board holding the Pawn
     * @return a list of legal moves for the Pawn
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        // Iterate through all offsets for the Pawn to determine legal moves
        for (final int currentCandidateOffset : PAWN_CANDIDATE_MOVE_COORD) {
            // Determine the first destination coordinate
            final int candidateDestinationCoordinate =
                    this.piecePosition +
                    (this.pieceAlliance.getDirection() * currentCandidateOffset);
            // If the pawn is at the bottom or top row, it cannot progress more
            if (!IsValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            // Non-attacking chess.game.move
            if (isNonAttackingMove(currentCandidateOffset, candidateDestinationCoordinate, board)) {
                // TODO: implement pawn promotion
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            } else if (canDoubleJump(currentCandidateOffset, candidateDestinationCoordinate)) { // Double jump
                // Get the coordinate of the chess.game.tile behind the candidate destination chess.game.tile
                final int coordinateBehindCandidate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);

                if (!board.getTile(coordinateBehindCandidate).isTileOccupied() &&
                    !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    // Once all checks pass, the pawn can jump two empty tiles
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }
            } else if (currentCandidateOffset == 7 &&
                      !IsPawnOnFirstOrEighthColumn(candidateDestinationCoordinate, this)) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    // Determine the opponent's chess.game.piece when not attacking in the first or eighth columns
                    final Piece pieceOnCandidateTile = board.getTile(candidateDestinationCoordinate).getPieceOnTile();
                    // TODO: attacking into a pawn promotion
                    if (this.pieceAlliance != pieceOnCandidateTile.getPieceAlliance()) {
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }
                }
            } else if (currentCandidateOffset == 9 &&
                      !IsPawnOnFirstOrEighthColumnReversed(candidateDestinationCoordinate, this)) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    // Determine the opponent's chess.game.piece when not attacking in the first or eighth columns
                    final Piece pieceOnCandidateTile = board.getTile(candidateDestinationCoordinate).getPieceOnTile();
                    // TODO: attacking into a pawn promotion
                    if (this.pieceAlliance != pieceOnCandidateTile.getPieceAlliance()) {
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    }
                }
            }

        }

        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * @param move what the chess.game.piece is doing
     * @return a moved chess.game.piece
     */
    @Override
    public Pawn movePiece(final Move move) {
        return new Pawn(this.piecePosition, move.getMovedPiece().getPieceAlliance());
    }

    /**
     * Determines the first half of if the pawn can do a double jump.
     * @param currentCandidateOffset the offset of the pawn's destination
     * @param candidateDestinationCoordinate the coordinate of the pawn's destination
     * @return the first half of whether the pawn can double jump
     */
    private boolean canDoubleJump(final int currentCandidateOffset, final int candidateDestinationCoordinate) {
        return  currentCandidateOffset == 16 &&
                this.isFirstMove() &&
                IsPawnOnSecondOrSeventhRow(candidateDestinationCoordinate, this);
    }

    /**
     * Determines if the pawn chess.game.move is non-attacking.
     * @param currentCandidateOffset the offset of the pawn's destination
     * @param candidateDestinationCoordinate the coordinate of the pawn's destination
     * @param board the chess chess.game.board
     * @return whether the pawn's chess.game.move is non-attacking
     */
    private boolean isNonAttackingMove(
            final int currentCandidateOffset,
            final int candidateDestinationCoordinate,
            final Board board) {
        return currentCandidateOffset == 8 && !(board.getTile(candidateDestinationCoordinate).isTileOccupied());
    }
}
