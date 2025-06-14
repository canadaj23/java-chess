package chess.game.tile;

import chess.game.piece.Piece;

import static chess.game.tile.TileManager.EMPTY_TILES_CACHE;

/**
 * This class is a blueprint for different types of tiles.
 * E.g., empty or occupied tiles
 */
public abstract class Tile {
    protected final int tileCoordinate;

    /**
     * Constructor for a Tile object.
     * @param tileCoordinate the coordinate of the chess.game.tile
     */
    protected Tile(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    /**
     * Creates a chess.game.tile for the chess chess.game.board.
     * @param tileCoordinate where the chess.game.tile should be
     * @param piece what chess.game.piece should be on the chess.game.tile
     * @return a chess.game.tile with a coordinate and a possible chess.game.piece
     */
    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    /**
     * @return the chess.game.tile's coordinate
     */
    public int getTileCoordinate() {
        return this.tileCoordinate;
    }

    //---------------------------- Abstract Methods ----------------------------
    /**
     * Determines an empty/occupied chess.game.tile.
     * @return whether the chess.game.tile is occupied
     */
    public abstract boolean isTileOccupied();

    /**
     * Determines what the chess.game.piece on the chess.game.tile is.
     * @return the chess.game.piece on the chess.game.tile
     */
    public abstract Piece getPieceOnTile();
}
