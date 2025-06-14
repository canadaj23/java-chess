package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static chess.game.utils.Constants.BoardConstants.TILES_ON_BOARD;
import static main.Game.BOARD_PANEL_DIMENSION;

/**
 * This class will draw the board elements onto a panel that can then be displayed on a GameWindow object.
 */
public class BoardPanel extends JPanel {
    private final Game game;
    private final List<TilePanel> boardTiles;

    /**
     * Constructor for a GamePanel object.
     */
    public BoardPanel(final Game game) {
        super(new GridLayout(8, 8));
        this.game = game;
        this.boardTiles = new ArrayList<TilePanel>();

        addTilePanels(boardTiles);
    }

    /**
     * Adds each TilePanel object to boardTiles.
     * @param boardTiles what will store all the TilePanel objects.
     */
    private void addTilePanels(final List<TilePanel> boardTiles) {
        for (int i = 0; i < TILES_ON_BOARD; i++) {
            final TilePanel tilePanel = new TilePanel(this, i);

            this.boardTiles.add(tilePanel);
            add(tilePanel);
        }

        setPreferredSize(BOARD_PANEL_DIMENSION);
        validate();
    }
}
