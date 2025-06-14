package chess.game.piece;

import chess.game.board.Board;
import chess.game.move.Move;
import chess.game.piece.attributes.Alliance;
import chess.game.piece.attributes.PieceType;

import java.util.Collection;

/**
 * This class serves as a blueprint for various chess pieces.
 */
public abstract class Piece {
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean firstMove;
    protected PieceType pieceType;
    protected boolean isKing = false;
    protected boolean isRook = false;
    private final int cashedHashCode;

    /**
     * Constructor for a Piece.
     *
     * @param piecePosition the position of the chess.game.piece
     * @param pieceAlliance black/white
     */
    protected Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        // TODO: implement more attributes for pieces
        this.firstMove = false;
        this.cashedHashCode = computeHashCode();
    }

    /**
     * Determines the alliance of the chess.game.piece.
     * @return black/white
     */
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    /**
     * Determines the coordinate of the chess.game.piece.
     * @return coordinate of the chess.game.piece
     */
    public int getPiecePosition() {
        return this.piecePosition;
    }

    /**
     * @return whether it is the chess.game.piece's first chess.game.move
     */
    public boolean isFirstMove() {
        return this.firstMove;
    }

    /**
     * @return the chess.game.piece name
     */
    public PieceType getPieceType() {
        return this.pieceType;
    }

    /**
     * @return whether the chess.game.piece is a King
     */
    public boolean isKing() {
        return this.isKing;
    }

    /**
     * @return whether the chess.game.piece is a Rook
     */
    public boolean isRook() {
        return this.isRook;
    }

    /**
     * @return the name of each chess.game.piece as a String
     */
    @Override
    public String toString() {
        return this.pieceType.toString();
    }

    /**
     * An overridden equals method to compare two pieces.
     * @param other the other chess.game.piece
     * @return if two pieces are equal
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Piece)) {
            return false;
        }

        final Piece otherPiece = (Piece) other;

        return piecePosition == otherPiece.piecePosition && pieceType.equals(otherPiece.getPieceType()) &&
                pieceAlliance == otherPiece.getPieceAlliance() && isFirstMove() == otherPiece.isFirstMove();
    }

    /**
     * @return a new hash code for a chess.game.piece
     */
    private int computeHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove() ? 1 : 0);

        return result;
    }

    /**
     * An overridden hashCode method.
     * @return the hash code of the chess.game.piece
     */
    @Override
    public int hashCode() {
        return this.cashedHashCode;
    }

    //---------------------------- Abstract Methods ----------------------------
    /**
     * Calculates the moves of a given chess.game.piece.
     * @param board the chess.game.board holding the chess.game.piece
     * @return a list of legal moves for a given chess.game.piece
     */
    public abstract Collection<Move> calculateLegalMoves(final Board board);

    /**
     * @param move what the chess.game.piece is doing
     * @return a moved chess.game.piece
     */
    public abstract Piece movePiece(final Move move);
}
