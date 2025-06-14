package main;

import java.awt.*;

/**
 * This class will take care of most, if not all, of the gameplay.
 */
public class Game {
    private static final float SCALE = 2.0f;
    protected static final Dimension OUTER_FRAME_DIMENSION = new Dimension((int) (600 * SCALE), (int) (600 * SCALE));
    protected static final Dimension BOARD_PANEL_DIMENSION = new Dimension((int) (400 * SCALE), (int) (350 * SCALE));
    protected static final Dimension TILE_PANEL_DIMENSION = new Dimension((int) (10 * SCALE), (int) (10 * SCALE));

    private final BoardWindow boardWindow;
    private final BoardPanel boardPanel;

    public Game() {
        boardPanel = new BoardPanel(this);
        boardWindow = new BoardWindow(boardPanel);

        boardPanel.setFocusable(true);
        boardPanel.requestFocus();
    }
}
