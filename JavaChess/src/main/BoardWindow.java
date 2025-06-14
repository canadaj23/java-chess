package main;

import javax.swing.*;
import java.awt.*;

import static main.Game.OUTER_FRAME_DIMENSION;

public class BoardWindow extends JFrame {
    private final JFrame gameFrame;

    /**
     * Constructor for a GameWindow object.
     * @param boardPanel the gamePanel object used for various purposes
     */
    public BoardWindow(final BoardPanel boardPanel) {

        this.gameFrame = new JFrame("Chess in Java");
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setLayout(new BorderLayout());

        final JMenuBar windowMenuBar = createWindowMenuBar();
        this.gameFrame.setJMenuBar(windowMenuBar);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.add(boardPanel, BorderLayout.CENTER);
        this.gameFrame.setResizable(false);
        this.gameFrame.pack();
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setVisible(true);
    }

    /**
     * @return the window menu bar with all menu options
     */
    private JMenuBar createWindowMenuBar() {
        final JMenuBar windowMenuBar = new JMenuBar();
        windowMenuBar.add(createFileMenuItem());

        return windowMenuBar;
    }

    /**
     * @return the File menu option
     */
    private JMenu createFileMenuItem() {
        // First menu option
        final JMenu fileMenu = new JMenu("File");

        // Open PGN file functionality
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        openPGN.addActionListener(actionEvent -> System.out.println("Open the PGN file!"));
        fileMenu.add(openPGN);

        // Exit game functionality
        final JMenuItem exitGame = new JMenuItem("Exit");
        exitGame.addActionListener(actionEvent -> System.exit(0));
        fileMenu.add(exitGame);

        return fileMenu;
    }
}
