package common;

public class Validator {
    private static final int MIN_GRID = 2;
    private static final int MAX_GRID = 1000;

    private Validator() {
    }

    public static boolean areValidDimensions(int rows, int cols) {
        return (cols >= MIN_GRID && cols <= rows) && rows < MAX_GRID;
    }

    public static int isValidCellValue(String token) {
        int value = Integer.parseInt(token);

        if (value == 0 || value == 1) {
            return value;
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_CELL_VALUE);
        }
    }

    public static boolean areValidCoordinates(int x1, int y1, int cols, int rows) {
        return (x1 >= 0 && x1 < cols) && (y1 >= 0 && y1 < rows);
    }

    public static boolean isValidIndex(int x1, int y1, int x, int y) {
        return (x1 >= 0 && x1 < x) && (y1 >= 0 && y1 < y);
    }


}
