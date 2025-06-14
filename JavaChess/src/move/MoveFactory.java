package move;

import board.Board;

import static move.Move.NULL_MOVE;

public class MoveFactory {

    private MoveFactory() {
        throw new RuntimeException("Not instantiable!");
    }

    /**
     * Creates a move based on the action of the piece.
     * @param board the chess board
     * @param currentCoordinate where the piece is currently
     * @param destinationCoordinate where the piece is trying to reach
     * @return a move based off what the piece wants to do
     */
    public static Move CreateMove(final Board board, final int currentCoordinate, final int destinationCoordinate) {
        for (final Move move : board.getAllLegalMoves()) {
            if (move.getCurrentCoordinate() == currentCoordinate &&
                move.getDestinationCoordinate() == destinationCoordinate) {
                return move;
            }
        }

        return NULL_MOVE;
    }
}
