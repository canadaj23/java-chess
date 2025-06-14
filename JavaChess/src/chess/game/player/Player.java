package chess.game.player;

import chess.game.board.Board;
import chess.game.move.Move;
import chess.game.move.MoveTransition;
import chess.game.piece.attributes.Alliance;
import chess.game.piece.pieces.King;
import chess.game.piece.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static chess.game.move.MoveStatus.*;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    private final boolean inCheck;

    /**
     * Constructor for a Player object.
     * @param board the chess chess.game.board
     * @param legalMoves the current chess.game.player's starting legal moves
     * @param opponentLegalMoves the opponent's starting legal moves
     */
    protected Player(
            final Board board,
            final Collection<Move> legalMoves,
            final Collection<Move> opponentLegalMoves) {
        this.board = board;
        this.playerKing = establishKing();
        legalMoves.addAll(calculateKingCastles(legalMoves, opponentLegalMoves));
        this.legalMoves = Collections.unmodifiableCollection(legalMoves);
        this.inCheck = !CalculateAttacksOnKing(this.playerKing.getPiecePosition(), opponentLegalMoves).isEmpty();
    }

    /**
     * Makes sure the chess.game.player has a King.
     * @return a King for the chess.game.player
     */
    private King establishKing() {
        for (final Piece piece : getActivePieces()) {
            if (piece.isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Not a valid chess.game.board!");
    }

    /**
     * Determines what attack moves affect the King.
     * @param piecePosition where the chess.game.piece is
     * @param moves the collection of moves
     * @return a list of attack moves affecting the King
     */
    protected static Collection<Move> CalculateAttacksOnKing(final int piecePosition, final Collection<Move> moves) {
        final List<Move> attackMoves = new ArrayList<>();

        // Search for attack moves
        for (final Move move : moves) {
            if (piecePosition == move.getDestinationCoordinate()) {
                attackMoves.add(move);
            }
        }

        return Collections.unmodifiableList(attackMoves);
    }

    /**
     * Determines if the chess.game.move is legal.
     * @param move the chess.game.move in question
     * @return whether the chess.game.move is legal
     */
    public boolean isMoveLegal(final Move move) {
        return this.legalMoves.contains(move);
    }

    /**
     * @return whether the King is in check
     */
    public boolean isInCheck() {
        return this.inCheck;
    }

    /**
     * Player is in check and cannot escape.
     * @return whether the chess.game.piece is in checkmate
     */
    public boolean isInCheckmate() {
        return this.inCheck && !canEscape();
    }

    /**
     * @return whether the King can escape
     */
    protected boolean canEscape() {
        for (final Move move : this.legalMoves) {
            final MoveTransition moveTransition = makeMoveTransition(move);

            if (moveTransition.getMoveStatus().isDone()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Player is not in check and cannot escape.
     * @return whether the chess.game.piece is in a stalemate
     */
    public boolean isInStalemate() {
        return !this.inCheck && !canEscape();
    }

    /**
     * TODO: implement
     * @return whether the chess.game.piece has castled
     */
    public boolean isCastled() {
        return false;
    }

    /**
     * @param move the chess.game.move to be made
     * @return a transition of moves
     */
    public MoveTransition makeMoveTransition(final Move move) {
        // Figure out if the chess.game.move is illegal
        if (!isMoveLegal(move)) {
            return new MoveTransition(this.board, move, ILLEGAL);
        }

        // Figure out the chess.game.move's designation when not illegal
        final Board transitionBoard = move.executeMove();
        final Collection<Move> kingAttacks = CalculateAttacksOnKing(
                transitionBoard.getCurrentPlayer().getOpponent().getPlayerKing().getPiecePosition(),
                transitionBoard.getCurrentPlayer().getLegalMoves());

        if (!kingAttacks.isEmpty()) {
            // Designate the chess.game.move as a check chess.game.move since the King can be attacked
            return new MoveTransition(this.board, move, IN_CHECK);
        }

        // Designate the chess.game.move as done if the chess.game.move is legal and not a check chess.game.move
        return new MoveTransition(transitionBoard, move, DONE);
    }

    /**
     * @return the chess.game.player's king
     */
    public King getPlayerKing() {
        return this.playerKing;
    }

    /**
     * @return the chess.game.piece's legal moves
     */
    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    //---------------------------- Abstract Methods ----------------------------
    /**
     * @return the chess.game.player's active pieces
     */
    public abstract Collection<Piece> getActivePieces();

    /**
     * @return the chess.game.player's alliance
     */
    public abstract Alliance getAlliance();

    /**
     * @return the chess.game.player's opponent
     */
    public abstract Player getOpponent();

    /**
     * @param legalPlayerMoves the chess.game.player's legal moves
     * @param legalOpponentMoves the opponent's legal moves
     * @return the legal castling moves for a chess.game.player
     */
    protected abstract Collection<Move> calculateKingCastles(
            final Collection<Move> legalPlayerMoves,
            final Collection<Move> legalOpponentMoves);
}
