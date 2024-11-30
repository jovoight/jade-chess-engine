package jade.board;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = getColumn(0);
    public static final boolean[] SECOND_COLUMN = getColumn(1);
    public static final boolean[] SEVENTH_COLUMN = getColumn(2);
    public static final boolean[] EIGHTH_COLUMN = getColumn(3);
    public static final boolean[] SECOND_ROW = getRow(1);
    public static final boolean[] SEVENTH_ROW = getRow(6);

    private BoardUtils() { throw new RuntimeException("Utility class"); }

    public static boolean isValidDestination(final int destination) {
        return destination >= 0 && destination < 64;
    }

    private static boolean[] getColumn(final int columnNumber) {
        final boolean[] column = new boolean[64];
        for (int i = columnNumber; i < 64; i += 8) { column[i] = true; }
        return column;
    }

    private static boolean[] getRow(final int rowNumber) {
        final boolean[] row = new boolean[64];
    }
}
