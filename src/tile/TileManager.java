package tile;

import piece.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static utils.Constants.BoardConstants.TILES_ON_BOARD;

/**
 * This class will hold methods that initialize/maintain any Tile subclasses.
 */
public class TileManager {
    
    protected static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = CreateAllEmptyTiles();

    /**
     * Constructor for a TileManager object.
     */
    public TileManager() {
    }

    /**
     * Generates a Map that holds all possible empty tiles.
     * @return a map containing all possible empty tiles
     */
    private static Map<Integer, EmptyTile> CreateAllEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < TILES_ON_BOARD; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return Collections.unmodifiableMap(emptyTileMap);
    }

    /**
     * Creates an OccupiedTile/EmptyTile based on if a piece is on the tile.
     * @param tileCoordinate the coordinate of the tile
     * @param piece the piece that may occupy the tile
     * @return an OccupiedTile/EmptyTile
     */
    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }
}
