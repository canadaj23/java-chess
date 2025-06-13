package piece;

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
         * @return whether the piece is white
         */
        @Override
        public boolean isWhite() {
            return false;
        }

        /**
         * @return whether the piece is black
         */
        @Override
        public boolean isBlack() {
            return true;
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
         * @return whether the piece is white
         */
        @Override
        public boolean isWhite() {
            return true;
        }

        /**
         * @return whether the piece is black
         */
        @Override
        public boolean isBlack() {
            return false;
        }
    };

    /**
     * @return -1 or 1 depending on the alliance
     */
    public abstract int getDirection();

    /**
     * @return whether the piece is white
     */
    public abstract boolean isWhite();

    /**
     * @return whether the piece is black
     */
    public abstract boolean isBlack();
}
