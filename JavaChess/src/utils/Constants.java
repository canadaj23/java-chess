package utils;

public class Constants {

    public static class BoardConstants {
        public static final int TILES_ON_BOARD = 64;
    }

    public static class PieceConstants {
        public static final int[] KNIGHT_CANDIDATE_MOVE_COORDS = {-17, -15, -10, -6, 6, 10, 15, 17};
        public static final int[] BISHOP_CANDIDATE_MOVE_VECTOR_COORDS = {-9, -7, 7, 9};
    }
}
