package chess.game.tile;

import chess.game.piece.Piece;

/**
 * This class holds everything OccupiedTile-related.
 */
public final class OccupiedTile extends Tile {
    private final Piece pieceOnTile;
    /**
     * Constructor for an OccupiedTile object.
     *
     * @param tileCoordinate the coordinate of the chess.game.tile
     */
    public OccupiedTile(final int tileCoordinate, final Piece pieceOnTile) {
        super(tileCoordinate);
        this.pieceOnTile = pieceOnTile;
    }

    /**
     * Determines an empty/occupied chess.game.tile.
     *
     * @return true since the chess.game.tile is occupied
     */
    @Override
    public boolean isTileOccupied() {
        return true;
    }

    /**
     * Determines what the chess.game.piece on the chess.game.tile is.
     *
     * @return the chess.game.piece on the chess.game.tile
     */
    @Override
    public Piece getPieceOnTile() {
        return pieceOnTile;
    }

    /**
     * @return the chess.game.piece in String form
     */
    @Override
    public String toString() {
        return getPieceOnTile().getPieceAlliance().isBlack() ?
                getPieceOnTile().toString().toLowerCase() : pieceOnTile.toString();
    }
}
