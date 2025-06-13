package utils;

/**
 * This class is simply for constants for particular classes.
 */
public class Constants {

    public static class BoardConstants {
        public static final int TILES_ON_BOARD = 64;
        public static final int TILES_PER_ROW = 8;
    }

    public static class PieceConstants {
        public static final int[] KNIGHT_CANDIDATE_MOVE_COORDS = {-17, -15, -10, -6, 6, 10, 15, 17};
        public static final int[] BISHOP_CANDIDATE_MOVE_VECTOR_COORDS = {-9, -7, 7, 9};
        public static final int[] ROOK_CANDIDATE_MOVE_VECTOR_COORDS = {-8, -1, 1, 8};
        public static final int[] QUEEN_OR_KING_CANDIDATE_MOVE_VECTOR_COORDS = {-9, -8, -7, -1, 1, 7, 8, 9};
        public static final int[] PAWN_CANDIDATE_MOVE_COORD = {7, 8, 9, 16};
    }
}
