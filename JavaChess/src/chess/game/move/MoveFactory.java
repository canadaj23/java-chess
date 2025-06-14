package chess.game.move;

import chess.game.board.Board;

import static chess.game.move.Move.NULL_MOVE;

public class MoveFactory {

    private MoveFactory() {
        throw new RuntimeException("Not instantiable!");
    }

    /**
     * Creates a chess.game.move based on the action of the chess.game.piece.
     * @param board the chess chess.game.board
     * @param currentCoordinate where the chess.game.piece is currently
     * @param destinationCoordinate where the chess.game.piece is trying to reach
     * @return a chess.game.move based off what the chess.game.piece wants to do
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
