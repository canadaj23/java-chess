package utils;

import board.Board;
import move.other.AttackMove;
import move.other.MajorMove;
import move.Move;
import piece.attributes.Alliance;
import piece.Piece;
import tile.Tile;

import java.util.List;

import static utils.Constants.BoardConstants.TILES_ON_BOARD;

/**
 * Contains methods that help other classes with logic, etc.
 */
public class HelperMethods {

    /**
     * Constructor for HelperMethods that will not be publicly accessed.
     */
    private HelperMethods() {
        throw new RuntimeException("This class cannot be instantiated!");
    }

    /**
     * Searches for all legal moves for a sliding piece (i.e., Bishop, Rook, and Queen).
     * @param candidateDestinationCoordinate the coordinate of the tile the piece wants to reach
     * @param currentPiece the sliding piece
     * @param board the chess board
     * @param legalMoves the list of legal moves for the sliding piece
     * @param currentCandidateOffset the current offset for the piece to move by
     */
    public static void FindSlidingPieceLegalMoves(
            int candidateDestinationCoordinate,
            final Piece currentPiece,
            final Board board,
            final List<Move> legalMoves,
            final int currentCandidateOffset) {
        // Continue while the sliding piece is within the bounds of the board
        while (IsValidTileCoordinate(candidateDestinationCoordinate)) {
            // Determine if the sliding piece on the 1st or 8th columns
            if (IsSlidingPieceColumnExcluded(candidateDestinationCoordinate)) {
                break;
            }
            // Obtain the destination tile with the new coordinate
            final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
            // Determine what move the sliding piece is trying to do
            if (!candidateDestinationTile.isTileOccupied()) {
                // An empty tile makes the move a MajorMove
                legalMoves.add(
                        new MajorMove(board,
                                currentPiece,
                                candidateDestinationCoordinate));
            } else {
                // Determine what the sliding piece and its alliance are
                final Piece pieceAtLocation = candidateDestinationTile.getPieceOnTile();
                final Alliance pieceAlliance = pieceAtLocation.getPieceAlliance();

                if (currentPiece.getPieceAlliance() != pieceAlliance) {
                    // The move is an AttackMove
                    legalMoves.add(new AttackMove(
                            board,
                            currentPiece,
                            candidateDestinationCoordinate,
                            pieceAtLocation));
                }
                // There is no need to continue using the current offset if a piece is in the way
                break;
            }
            candidateDestinationCoordinate += currentCandidateOffset;
        }
    }

    /**
     * Determines if the tile is within 0 and 63 tiles.
     * @param coordinate the coordinate of the tile
     * @return whether the tile is within the board
     */
    public static boolean IsValidTileCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < TILES_ON_BOARD;
    }

    /**
     * Determines if the column the piece wants to move to is on the 1st or 8th columns.
     * @param candidateDestinationCoordinate where the piece wants to move to
     * @return if the piece's destination is the 1st or 8th columns
     */
    public static boolean IsSlidingPieceColumnExcluded(int candidateDestinationCoordinate) {
        int column = (candidateDestinationCoordinate % 8) + 1;
        return column == 1 || column == 8;
    }


    /**
     * Determines the row (2 or 7) of a pawn.
     * @param candidateDestinationCoordinate the coordinate of the pawn's destination
     * @param pawn the pawn to be placed
     * @return what row the pawn should be on
     */
    public static boolean IsPawnOnSecondOrSeventhRow(int candidateDestinationCoordinate, Piece pawn) {
        int row = (candidateDestinationCoordinate % 8) + 1;
        return (row == 2 && pawn.getPieceAlliance().isBlack()) || (row == 7 && pawn.getPieceAlliance().isWhite());
    }

    /**
     * Determines the column (1 or 8) of a pawn.
     * @param candidateDestinationCoordinate the coordinate of the pawn's destination
     * @param pawn the pawn to be placed
     * @return what column the pawn should be on
     */
    public static boolean IsPawnOnFirstOrEighthColumn(int candidateDestinationCoordinate, Piece pawn) {
        int column = (candidateDestinationCoordinate % 8) + 1;
        return (column == 1 && pawn.getPieceAlliance().isBlack()) || (column == 8 && pawn.getPieceAlliance().isWhite());
    }

    /**
     * Determines the column (1 or 8) of a pawn.
     * @param candidateDestinationCoordinate the coordinate of the pawn's destination
     * @param pawn the pawn to be placed
     * @return what column the pawn should be on
     */
    public static boolean IsPawnOnFirstOrEighthColumnReversed(int candidateDestinationCoordinate, Piece pawn) {
        int column = (candidateDestinationCoordinate % 8) + 1;
        return (column == 1 && pawn.getPieceAlliance().isWhite()) || (column == 8 && pawn.getPieceAlliance().isBlack());
    }
}
