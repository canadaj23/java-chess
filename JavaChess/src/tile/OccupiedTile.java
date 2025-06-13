package tile;

import piece.Piece;

/**
 * This class holds everything OccupiedTile-related.
 */
public final class OccupiedTile extends Tile {
    private final Piece pieceOnTile;
    /**
     * Constructor for an OccupiedTile object.
     *
     * @param tileCoordinate the coordinate of the tile
     */
    public OccupiedTile(final int tileCoordinate, final Piece pieceOnTile) {
        super(tileCoordinate);
        this.pieceOnTile = pieceOnTile;
    }

    /**
     * Determines an empty/occupied tile.
     *
     * @return true since the tile is occupied
     */
    @Override
    public boolean isTileOccupied() {
        return true;
    }

    /**
     * Determines what the piece on the tile is.
     *
     * @return the piece on the tile
     */
    @Override
    public Piece getPieceOnTile() {
        return pieceOnTile;
    }

    /**
     * @return the piece in String form
     */
    @Override
    public String toString() {
        return getPieceOnTile().toString();
    }
}
