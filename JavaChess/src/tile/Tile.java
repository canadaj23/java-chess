package tile;

import piece.Piece;

/**
 * This class is a blueprint for different types of tiles.
 * E.g., empty or occupied tiles
 */
public abstract class Tile {
    protected final int tileCoordinate;

    /**
     * Constructor for a Tile object.
     * @param tileCoordinate the coordinate of the tile
     */
    protected Tile(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    /**
     * Determines an empty/occupied tile.
     * @return whether the tile is occupied
     */
    public abstract boolean isTileOccupied();

    /**
     * Determines what the piece on the tile is.
     * @return the piece on the tile
     */
    public abstract Piece getPieceOnTile();
}
