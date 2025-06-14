package chess.game.move.other;

import chess.game.board.Board;
import chess.game.move.Move;
import chess.game.piece.Piece;

/**
 * This class pertains to attacking moves.
 */
public class AttackMove extends Move {
    private final Piece attackedPiece;

    /**
     * Constructor for an AttackMove object.
     * @param board the chess chess.game.board
     * @param movedPiece the chess.game.piece that wants to chess.game.move
     * @param destinationCoordinate the destination's coordinate on the chess.game.board
     * @param attackedPiece the chess.game.piece that is getting attacked
     */
    public AttackMove
            (final Board board,
             final Piece movedPiece,
             final int destinationCoordinate,
             final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinate);
        this.attackedPiece = attackedPiece;
    }

    /**
     * @return the new chess.game.board with the executed AttackMove
     */
    @Override
    public Board executeMove() {
        return null;
    }

    /**
     * @return whether the chess.game.move is an attack
     */
    @Override
    public boolean isAttack() {
        return true;
    }

    /**
     * @return the attacked chess.game.piece
     */
    @Override
    public Piece getAttackedPiece() {
        return this.attackedPiece;
    }

    /**
     * @return a hashcode for an AttackMove
     */
    @Override
    public int hashCode() {
        return this.attackedPiece.hashCode() + super.hashCode();
    }

    /**
     * @param other the other chess.game.move in question
     * @return whether the two moves are both an AttackMove
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AttackMove)) {
            return false;
        }

        final AttackMove otherAttackMove = (AttackMove) other;

        return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
    }
}
