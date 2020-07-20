import factories.CellFactory;
import factories.CellFactoryImpl;
import models.cell.Cell;

import java.util.*;

public class Main {
    private static CellFactory cellFactory = new CellFactoryImpl();
    private static final List<Integer> RED_TO_GREEN_RULE = Arrays.asList(3, 6);
    private static final List<Integer> GREEN_TO_RED_RULE = Arrays.asList(0, 1, 4, 5, 7, 8);
    private static Map<Cell, Integer> cellCollection = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int x = dimensions[0];//TODO - cols !!!
        int y = dimensions[1];//TODO - rows !!!
        //TODO Should I do input validation and check if X is not bigger than Y !!!

        int[][] matrix = new int[y][x];

        for (int i = 0; i < y; i++) {
            matrix[i] = Arrays.stream(scanner.nextLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        int[] line = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int x1 = line[0];
        int y1 = line[1];
        int n = line[2];

        int targetValue = matrix[y1][x1];
        int counter = 0;

        if (getTargetColour(targetValue)) {
            counter++;
        }

        while (n-- > 0) {
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    int cellValue = matrix[row][col];

                    Cell cell = cellFactory.createCell(col, row, cellValue);
                    extractNeighbours(cell, x, y, matrix);
                    int greenis = countGreenis(cell);
                    cellCollection.put(cell, greenis);
                }
            }
            applyRules(cellCollection, matrix);

            if (getTargetColour(matrix[y1][x1])) {
                counter++;
            }

            cellCollection.clear();
        }
        System.out.println(counter);
    }

    private static boolean getTargetColour(int targetValue) {
        return targetValue == 1;
    }

    private static void applyRules(Map<Cell, Integer> cellCollection, int[][] generation) {
        for (Map.Entry<Cell, Integer> entry : cellCollection.entrySet()) {

            Cell cell = entry.getKey();
            int greenis = entry.getValue();
            int col = cell.getX();
            int row = cell.getY();

            if (cell.getClass().getSimpleName().equals("Green")
                    && GREEN_TO_RED_RULE.contains(greenis)) {
                generation[row][col] = 0;
            }

            if (cell.getClass().getSimpleName().equals("Red")
                    && RED_TO_GREEN_RULE.contains(greenis)) {
                generation[row][col] = 1;
            }
        }
    }

    private static int countGreenis(Cell cell) {
        return ((int) cell.getNeighbours()
                .stream()
                .filter(c -> c.getClass().getSimpleName().equals("Green"))
                .count());
    }

    private static void extractNeighbours(Cell cell, int x, int y, int[][] matrix) {
        int x1 = cell.getX();//COL
        int y1 = cell.getY();//ROW

        if (isValidIndex(x1, y1, x, y)) {
            if (isValidIndex(x1 + 1, y1, x, y))
                cell.addNeighbour(cellFactory.createCell(x1 + 1, y1, matrix[y1][x1 + 1]));

            if (isValidIndex(x1 - 1, y1, x, y))
                cell.addNeighbour(cellFactory.createCell(x1 - 1, y1, matrix[y1][x1 - 1]));

            if (isValidIndex(x1, y1 + 1, x, y))
                cell.addNeighbour(cellFactory.createCell(x1, y1 + 1, matrix[y1 + 1][x1]));

            if (isValidIndex(x1, y1 - 1, x, y))
                cell.addNeighbour(cellFactory.createCell(x1, y1 - 1, matrix[y1 - 1][x1]));

            if (isValidIndex(x1 - 1, y1 + 1, x, y))
                cell.addNeighbour(cellFactory.createCell(x1 - 1, y1 + 1, matrix[y1 + 1][x1 - 1]));

            if (isValidIndex(x1 + 1, y1 - 1, x, y))
                cell.addNeighbour(cellFactory.createCell(x1 + 1, y1 - 1, matrix[y1 - 1][x1 + 1]));

            if (isValidIndex(x1 + 1, y1 + 1, x, y))
                cell.addNeighbour(cellFactory.createCell(x1 + 1, y1 + 1, matrix[y1 + 1][x1 + 1]));

            if (isValidIndex(x1 - 1, y1 - 1, x, y))
                cell.addNeighbour(cellFactory.createCell(x1 - 1, y1 - 1, matrix[y1 - 1][x1 - 1]));
        }
    }

    private static boolean isValidIndex(int x1, int y1, int x, int y) {
        return ((x1 >= 0 && x1 < x) && (y1 >= 0 && y1 < y));
    }

}





