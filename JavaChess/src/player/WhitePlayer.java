package player;

import board.Board;
import move.Move;
import move.castle.KingSideCastleMove;
import move.castle.QueenSideCastleMove;
import piece.attributes.Alliance;
import piece.Piece;
import piece.pieces.Rook;
import tile.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static piece.attributes.Alliance.WHITE;

public class WhitePlayer extends Player {

    /**
     * Constructor for a WhitePlayer object.
     * @param board the chess board
     * @param whiteInitialLegalMoves white player's starting legal moves
     * @param blackInitialLegalMoves black player's starting legal moves
     */
    public WhitePlayer(
            final Board board,
            final Collection<Move> whiteInitialLegalMoves,
            final Collection<Move> blackInitialLegalMoves) {
        super(board, whiteInitialLegalMoves, blackInitialLegalMoves);
    }

    /**
     * @return the white pieces
     */
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    /**
     * @return the white player's alliance
     */
    @Override
    public Alliance getAlliance() {
        return WHITE;
    }

    /**
     * @return the white player's opponent (black)
     */
    @Override
    public Player getOpponent() {
        return this.board.getOpponent(WHITE);
    }

    /**
     * @param legalPlayerMoves   the white player's legal moves
     * @param legalOpponentMoves the black player's legal moves
     * @return the legal castling moves for the white player
     */
    @Override
    public Collection<Move> calculateKingCastles(
            final Collection<Move> legalPlayerMoves,
            final Collection<Move> legalOpponentMoves) {
        final List<Move> kingCastles = new ArrayList<>();

        // Determine if it is the King's first move and is not in check
        if (this.playerKing.isFirstMove() && !isInCheck()) {
            // Then proceed to find the castling moves
            calculateAllPossibleCastles(kingCastles, legalOpponentMoves);
        }

        return Collections.unmodifiableList(kingCastles);
    }

    /**
     * Determine if the King has any King-side and/or Queen-side castles.
     * @param kingCastles        the list of all castles
     * @param legalOpponentMoves the opponent's legal moves
     */
    private void calculateAllPossibleCastles(
            final List<Move> kingCastles,
            final Collection<Move> legalOpponentMoves) {
        // Determine if the King-side tiles in between the King and Rook are empty
        if (!this.board.getTile(61).isTileOccupied() &&
            !this.board.getTile(62).isTileOccupied()) {
            final Tile rookTile = this.board.getTile(63);

            determineWhichSideCastlingMove(kingCastles, rookTile, 61, legalOpponentMoves);
        }

        // Determine if the Queen-side tiles in between the King and Rook are empty
        if (!this.board.getTile(57).isTileOccupied() &&
            !this.board.getTile(58).isTileOccupied() &&
            !this.board.getTile(59).isTileOccupied()) {
            final Tile rookTile = this.board.getTile(56);

            determineWhichSideCastlingMove(kingCastles, rookTile, 57, legalOpponentMoves);
        }
    }

    /**
     * Determines what moves are considered King/Queen-side castling moves.
     * @param kingCastles        the list of all castling moves
     * @param rookTile           what tile the King-side Rook may be on
     * @param tileNumber         which tile to search for emptiness
     * @param legalOpponentMoves the opponent's legal moves
     */
    private void determineWhichSideCastlingMove(
            final List<Move> kingCastles,
            final Tile rookTile,
            final int tileNumber,
            final Collection<Move> legalOpponentMoves) {
        // Determine if the King/Queen-side rook is present and has not moved yet
        if (rookTile.isTileOccupied() && rookTile.getPieceOnTile().isFirstMove()) {
            if (tileNumber == 61) { // For King-side
                if (CalculateAttacksOnKing(tileNumber, legalOpponentMoves).isEmpty() &&
                        CalculateAttacksOnKing(tileNumber + 1, legalOpponentMoves).isEmpty() &&
                        rookTile.getPieceOnTile().isRook()) {
                    kingCastles.add(new KingSideCastleMove(
                                                            this.board,
                                                            this.playerKing,
                                                            62,
                                                            (Rook) rookTile.getPieceOnTile()));
                }
            } else { // For Queen-side
                kingCastles.add(new QueenSideCastleMove(
                                                        this.board,
                                                        this.playerKing,
                                                        58,
                                                        (Rook) rookTile.getPieceOnTile()));
            }
        }
    }
}
