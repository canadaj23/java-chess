package board;

import move.Move;
import piece.*;
import tile.Tile;

import java.util.*;

import static piece.Alliance.*;
import static utils.Constants.BoardConstants.TILES_ON_BOARD;
import static utils.Constants.BoardConstants.TILES_PER_ROW;

/**
 * This class pertains to the chess board that uses the Builder pattern.
 */
public class Board {
    private final List<Tile> gameBoard;
    private final Collection<Piece> whitePieces, blackPieces;

    /**
     * Privately accessed constructor for a Board object.
     * @param boardBuilder what will build the chess board
     */
    private Board(final BoardBuilder boardBuilder) {
        this.gameBoard = CreateGameBoard(boardBuilder);
        this.whitePieces = CalculateActivePieces(this.gameBoard, WHITE);
        this.blackPieces = CalculateActivePieces(this.gameBoard, BLACK);

        final Collection<Move> whiteInitialLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackInitialLegalMoves = calculateLegalMoves(this.blackPieces);
    }

    /**
     * Creates the chess board using the boardBuilder object.
     * @param boardBuilder gives information about the chess board
     * @return a list of tiles for the chess board
     */
    private static List<Tile> CreateGameBoard(final BoardBuilder boardBuilder) {
        final Tile[] tiles = new Tile[TILES_ON_BOARD];

        for (int i = 0; i < TILES_ON_BOARD; i++) {
            tiles[i] = Tile.createTile(i, boardBuilder.boardConfiguration.get(i));
        }

        return List.of(tiles);
    }

    /**
     * Creates the starting board for a chess game.
     * @return a fresh chess board with no pieces moved
     */
    public static Board CreateInitialBoard() {
        // Create the pieces with a boardBuilder
        BoardBuilder boardBuilder = new BoardBuilder();
        CreatePieces(boardBuilder);

        // White has the starting move
        boardBuilder.setMoveMaker(WHITE);

        return boardBuilder.buildBoard();
    }

    private static void CreatePieces(final BoardBuilder boardBuilder) {
        // Black layout
        CreateBlackPieces(boardBuilder);

        // White layout
        CreateWhitePieces(boardBuilder);
    }

    /**
     * Creates the black pieces for the board.
     * @param boardBuilder what will make the black pieces
     */
    private static void CreateBlackPieces(final BoardBuilder boardBuilder) {
        // Make all but the black pawn pieces
        boardBuilder.setPiece(new Rook(0, BLACK));
        boardBuilder.setPiece(new Knight(1, BLACK));
        boardBuilder.setPiece(new Bishop(2, BLACK));
        boardBuilder.setPiece(new Queen(3, BLACK));
        boardBuilder.setPiece(new King(4, BLACK));
        boardBuilder.setPiece(new Bishop(5, BLACK));
        boardBuilder.setPiece(new Knight(6, BLACK));
        boardBuilder.setPiece(new Rook(7, BLACK));

        // Make the black pawn pieces
        for (int i = 8; i < 16; i++) {
            boardBuilder.setPiece(new Pawn(i, BLACK));
        }
    }

    /**
     * Creates the white pieces for the board.
     * @param boardBuilder what will make the white pieces
     */
    private static void CreateWhitePieces(BoardBuilder boardBuilder) {
        // Make the white pawn pieces
        for (int i = 48; i < 56; i++) {
            boardBuilder.setPiece(new Pawn(i, WHITE));
        }

        // Make the other white pieces
        boardBuilder.setPiece(new Rook(56, WHITE));
        boardBuilder.setPiece(new Knight(57, WHITE));
        boardBuilder.setPiece(new Bishop(58, WHITE));
        boardBuilder.setPiece(new Queen(59, WHITE));
        boardBuilder.setPiece(new King(60, WHITE));
        boardBuilder.setPiece(new Bishop(61, WHITE));
        boardBuilder.setPiece(new Knight(62, WHITE));
        boardBuilder.setPiece(new Rook(63, WHITE));
    }

    /**
     * Calculates all the active pieces for a given alliance.
     * @param gameBoard the chess board
     * @param alliance black/white
     * @return a collection of all pieces of an alliance
     */
    private static Collection<Piece> CalculateActivePieces(final List<Tile> gameBoard, final Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>();

        for (final Tile tile : gameBoard) {
            if (tile.isTileOccupied()) {
                final Piece piece = tile.getPieceOnTile();

                if (piece.getPieceAlliance() == alliance) {
                    // The piece is active
                    activePieces.add(piece);
                }
            }
        }

        return Collections.unmodifiableList(activePieces);
    }

    /**
     * Calculates all the alliance's pieces' legal moves.
     * @param alliancePieces the pieces of the alliance
     * @return all legal moves for the pieces of an alliance
     */
    private Collection<Move> calculateLegalMoves(final Collection<Piece> alliancePieces) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final Piece piece : alliancePieces) {
            legalMoves.addAll(piece.calculateLegalMoves(this));
        }

        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * Determines the tile with a given destination coordinate.
     * @param candidateDestinationCoordinate the coordinate of a potential destination tile
     * @return a candidate tile
     */
    public Tile getTile(final int candidateDestinationCoordinate) {
        return gameBoard.get(candidateDestinationCoordinate);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < TILES_ON_BOARD; i++) {
            final String tileText = this.gameBoard.get(i).toString();

            stringBuilder.append(String.format("%3s", tileText));
            if ((i + 1) % TILES_PER_ROW == 0) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    /**
     * This inner class will create the chess board.
     */
    public static class BoardBuilder {
        Map<Integer, Piece> boardConfiguration;
        Alliance nextMoveMaker;

        /**
         * Publicly accessed constructor for a BoardBuilder object.
         */
        public BoardBuilder() {
            this.boardConfiguration = new HashMap<>();
        }

        /**
         * Places the piece in the board map.
         * @param piece what will be places in the board map
         * @return the BoardBuilder object
         */
        public BoardBuilder setPiece(final Piece piece) {
            this.boardConfiguration.put(piece.getPiecePosition(), piece);
            return this;
        }

        /**
         * Designates the move maker.
         * @param nextMoveMaker who will make the next move
         * @return the BoardBuilder object
         */
        public BoardBuilder setMoveMaker(final Alliance nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        /**
         * @return a fresh chess board
         */
        public Board buildBoard() {
            return new Board(this);
        }
    }
}
