package tile;

import piece.Piece;

import static tile.TileManager.EMPTY_TILES_CACHE;

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
     * Creates a tile for the chess board.
     * @param tileCoordinate where the tile should be
     * @param piece what piece should be on the tile
     * @return a tile with a coordinate and a possible piece
     */
    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    /**
     * @return the tile's coordinate
     */
    public int getTileCoordinate() {
        return this.tileCoordinate;
    }

    //---------------------------- Abstract Methods ----------------------------
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
