package main;

import chess.game.board.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static chess.game.board.Board.CreateInitialBoard;
import static main.Game.TILE_PANEL_DIMENSION;

/**
 * This class will draw the tile elements onto a panel that can then be displayed on a GameWindow object.
 */
public class TilePanel extends JPanel {
    private final int tileID;
    private final Color lightTileColor = Color.decode("#eeeed2");
    private final Color darkTileColor = Color.decode("#69923e");
    private final Board chessBoard;
    private static String defaultPieceImagesPath = "res/";

    public TilePanel(final BoardPanel boardPanel, final int tileID) {
        super(new GridBagLayout());
        this.tileID = tileID;
        this.chessBoard = CreateInitialBoard();
        setPreferredSize(TILE_PANEL_DIMENSION);
        assignTileColor();
        assignTilePieceIcon(chessBoard);
        validate();
    }

    /**
     * Assigns the tile a color based on the chess board pattern.
     */
    private void assignTileColor() {
        boolean isLight = ((this.tileID + (this.tileID / 8)) % 2 == 0);
        setBackground(isLight ? lightTileColor : darkTileColor);
    }

    private void assignTilePieceIcon(final Board board) {
        this.removeAll();

        if (board.getTile(this.tileID).isTileOccupied()) {
            try {
                final BufferedImage pieceImage = ImageIO.read(new File(
                        defaultPieceImagesPath +
                        board.getTile(this.tileID).getPieceOnTile().getPieceAlliance().toString().charAt(0) +
                        board.getTile(this.tileID).getPieceOnTile().toString() + ".png"));
                add(new JLabel(new ImageIcon(pieceImage)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
