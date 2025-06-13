package move;

public enum MoveStatus {
    DONE {
        /**
         * @return true
         */
        @Override
        public boolean isDone() {
            return true;
        }
    },
    ILLEGAL {
        /**
         * @return false
         */
        @Override
        public boolean isDone() {
            return false;
        }
    },
    IN_CHECK {
        /**
         * @return false
         */
        @Override
        public boolean isDone() {
            return false;
        }
    };

    public abstract boolean isDone();
}
