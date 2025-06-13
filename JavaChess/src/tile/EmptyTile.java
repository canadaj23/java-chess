package tile;

import piece.Piece;

/**
 * This class holds everything EmptyTile-related.
 */
public final class EmptyTile extends Tile {

    /**
     * Constructor for an EmptyTile object.
     *
     * @param tileCoordinate the coordinate of the tile
     */
    public EmptyTile(final int tileCoordinate) {
        super(tileCoordinate);
    }

    /**
     * Determines an empty/occupied tile.
     *
     * @return false since the tile is empty
     */
    @Override
    public boolean isTileOccupied() {
        return false;
    }

    /**
     * Determines what the piece on the tile is.
     *
     * @return the piece on the tile
     */
    @Override
    public Piece getPieceOnTile() {
        return null;
    }

    /**
     * @return a String version of an empty tile
     */
    @Override
    public String toString() {
        return "-";
    }
}
