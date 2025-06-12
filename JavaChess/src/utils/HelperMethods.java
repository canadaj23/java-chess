package utils;

import static utils.Constants.BoardConstants.TILES_ON_BOARD;

/**
 * Contains methods that help other classes with logic, etc.
 */
public class HelperMethods {

    private HelperMethods() {
        throw new RuntimeException("This class cannot be instantiated!");
    }

    /**
     * Determines if the tile is within 0 and 63 tiles.
     * @param coordinate the coordinate of the tile
     * @return whether the tile is within the board
     */
    public static boolean IsValidTileCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < TILES_ON_BOARD;
    }
}
