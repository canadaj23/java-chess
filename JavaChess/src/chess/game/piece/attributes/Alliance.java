package chess.game.piece.attributes;

import chess.game.player.BlackPlayer;
import chess.game.player.Player;
import chess.game.player.WhitePlayer;

public enum Alliance {
    BLACK {
        /**
         * @return the direction of black
         */
        @Override
        public int getDirection() {
            return 1;
        }

        /**
         * @return whether the chess.game.piece is white
         */
        @Override
        public boolean isWhite() {
            return false;
        }

        /**
         * @return whether the chess.game.piece is black
         */
        @Override
        public boolean isBlack() {
            return true;
        }

        /**
         * Chooses whose turn it is.
         *
         * @param whitePlayer the white pieces
         * @param blackPlayer the black pieces
         * @return which chess.game.player's turn it is
         */
        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return blackPlayer;
        }
    },
    WHITE {
        /**
         * @return the direction of white
         */
        @Override
        public int getDirection() {
            return -1;
        }

        /**
         * @return whether the chess.game.piece is white
         */
        @Override
        public boolean isWhite() {
            return true;
        }

        /**
         * @return whether the chess.game.piece is black
         */
        @Override
        public boolean isBlack() {
            return false;
        }

        /**
         * Chooses whose turn it is.
         *
         * @param whitePlayer the white pieces
         * @param blackPlayer the black pieces
         * @return which chess.game.player's turn it is
         */
        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return whitePlayer;
        }
    };

    /**
     * @return -1 or 1 depending on the alliance
     */
    public abstract int getDirection();

    /**
     * @return whether the chess.game.piece is white
     */
    public abstract boolean isWhite();

    /**
     * @return whether the chess.game.piece is black
     */
    public abstract boolean isBlack();

    /**
     * Chooses whose turn it is.
     * @param whitePlayer the white pieces
     * @param blackPlayer the black pieces
     * @return which chess.game.player's turn it is
     */
    public abstract Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer);
}
