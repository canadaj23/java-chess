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
    },
    WHITE {
        /**
         * @return the direction of white
         */
        @Override
        public int getDirection() {
            return -1;
        }
    };

    /**
     * @return -1 or 1 depending on the alliance
     */
    public abstract int getDirection();
}
