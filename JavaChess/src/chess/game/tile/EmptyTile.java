package chess.game.tile;

import chess.game.piece.Piece;

/**
 * This class holds everything EmptyTile-related.
 */
public final class EmptyTile extends Tile {

    /**
     * Constructor for an EmptyTile object.
     *
     * @param tileCoordinate the coordinate of the chess.game.tile
     */
    public EmptyTile(final int tileCoordinate) {
        super(tileCoordinate);
    }

    /**
     * Determines an empty/occupied chess.game.tile.
     *
     * @return false since the chess.game.tile is empty
     */
    @Override
    public boolean isTileOccupied() {
        return false;
    }

    /**
     * Determines what the chess.game.piece on the chess.game.tile is.
     *
     * @return the chess.game.piece on the chess.game.tile
     */
    @Override
    public Piece getPieceOnTile() {
        return null;
    }

    /**
     * @return a String version of an empty chess.game.tile
     */
    @Override
    public String toString() {
        return "-";
    }
}
