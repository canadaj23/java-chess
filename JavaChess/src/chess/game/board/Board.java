package chess.game.board;

import chess.game.move.Move;
import chess.game.piece.*;
import chess.game.piece.attributes.Alliance;
import chess.game.piece.pieces.*;
import chess.game.player.BlackPlayer;
import chess.game.player.Player;
import chess.game.player.WhitePlayer;
import chess.game.tile.Tile;

import java.util.*;

import static chess.game.piece.attributes.Alliance.*;
import static chess.game.utils.Constants.BoardConstants.TILES_ON_BOARD;
import static chess.game.utils.Constants.BoardConstants.TILES_PER_ROW;

/**
 * This class pertains to the chess chess.game.board that uses the Builder pattern.
 */
public class Board {
    private final List<Tile> gameBoard;
    private final Collection<Piece> whitePieces, blackPieces;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;

    /**
     * Privately accessed constructor for a Board object.
     * @param boardBuilder what will build the chess chess.game.board
     */
    private Board(final BoardBuilder boardBuilder) {
        this.gameBoard = CreateGameBoard(boardBuilder);
        this.whitePieces = CalculateActivePieces(this.gameBoard, WHITE);
        this.blackPieces = CalculateActivePieces(this.gameBoard, BLACK);

        final Collection<Move> whiteInitialLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackInitialLegalMoves = calculateLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteInitialLegalMoves, blackInitialLegalMoves);
        this.blackPlayer = new BlackPlayer(this, whiteInitialLegalMoves, blackInitialLegalMoves);

        this.currentPlayer = boardBuilder.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);
    }

    /**
     * Creates the chess chess.game.board using the boardBuilder object.
     * @param boardBuilder gives information about the chess chess.game.board
     * @return a list of tiles for the chess chess.game.board
     */
    private static List<Tile> CreateGameBoard(final BoardBuilder boardBuilder) {
        final Tile[] tiles = new Tile[TILES_ON_BOARD];

        for (int i = 0; i < TILES_ON_BOARD; i++) {
            tiles[i] = Tile.createTile(i, boardBuilder.boardConfiguration.get(i));
        }

        return List.of(tiles);
    }

    /**
     * Creates the starting chess.game.board for a chess game.
     * @return a fresh chess chess.game.board with no pieces moved
     */
    public static Board CreateInitialBoard() {
        // Create the pieces with a boardBuilder
        BoardBuilder boardBuilder = new BoardBuilder();
        CreatePieces(boardBuilder);

        // White has the starting chess.game.move
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
     * Creates the black pieces for the chess.game.board.
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
     * Creates the white pieces for the chess.game.board.
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
     * @param gameBoard the chess chess.game.board
     * @param alliance black/white
     * @return a collection of all pieces of an alliance
     */
    private static Collection<Piece> CalculateActivePieces(final List<Tile> gameBoard, final Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>();

        for (final Tile tile : gameBoard) {
            if (tile.isTileOccupied()) {
                final Piece piece = tile.getPieceOnTile();

                if (piece.getPieceAlliance() == alliance) {
                    // The chess.game.piece is active
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

        return legalMoves;
    }

    /**
     * Determines the chess.game.tile with a given destination coordinate.
     * @param candidateDestinationCoordinate the coordinate of a potential destination chess.game.tile
     * @return a candidate chess.game.tile
     */
    public Tile getTile(final int candidateDestinationCoordinate) {
        return gameBoard.get(candidateDestinationCoordinate);
    }

    /**
     * @return all the white pieces
     */
    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    /**
     * @return all the black pieces
     */
    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    /**
     * @return the chess.game.player's opponent
     */
    public Player getOpponent(final Alliance alliance) {
        return alliance == BLACK ? whitePlayer : blackPlayer;
    }

    /**
     * @return the chess chess.game.board as Strings
     */
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
     * @return the current chess.game.player
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * @return all legal moves for both white and black
     */
    public Iterable<Move> getAllLegalMoves() {
        List<Move> allLegalMoves = new ArrayList<>();

        allLegalMoves.addAll(this.whitePlayer.getLegalMoves());
        allLegalMoves.addAll(this.blackPlayer.getLegalMoves());

        return Collections.unmodifiableList(allLegalMoves);

    }

    /**
     * This inner class will create the chess chess.game.board.
     */
    public static class BoardBuilder {
        Map<Integer, Piece> boardConfiguration;
        Alliance nextMoveMaker;
        Pawn enPassantPawn;

        /**
         * Publicly accessed constructor for a BoardBuilder object.
         */
        public BoardBuilder() {
            this.boardConfiguration = new HashMap<>();
        }

        /**
         * Sets the chess.game.piece on the chess.game.board.
         * @param piece what will be placed in the chess.game.board map
         * @return the BoardBuilder object
         */
        public BoardBuilder setPiece(final Piece piece) {
            this.boardConfiguration.put(piece.getPiecePosition(), piece);
            return this;
        }

        /**
         * Designates the chess.game.move maker.
         * @param nextMoveMaker who will make the next chess.game.move
         * @return the BoardBuilder object
         */
        public BoardBuilder setMoveMaker(final Alliance nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        /**
         * @return a fresh chess chess.game.board
         */
        public Board buildBoard() {
            return new Board(this);
        }

        /**
         * Sets the chess.game.piece on the chess.game.board as an En Passant Pawn.
         * @param enPassantPawn what will be placed in the chess.game.board map
         */
        public void setEnPassantPawn(final Pawn enPassantPawn) {
            this.enPassantPawn = enPassantPawn;
        }
    }
}
