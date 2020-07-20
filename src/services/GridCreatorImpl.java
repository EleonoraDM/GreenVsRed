package services;

import factories.CellFactory;
import factories.CellFactoryImpl;
import models.cell.Cell;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GridCreatorImpl implements GridCreator {
    private CellFactory factory;
    private Map<Cell, Integer> grid;

    public GridCreatorImpl() {
        this.factory = new CellFactoryImpl();
        this.grid = new LinkedHashMap<>();
    }

    @Override
    public Map<Cell, Integer> getCells(int row, int col, int x, int y, int[][] matrix) {
        int cellValue = matrix[row][col];

        Cell cell = factory.createCell(col, row, cellValue);
        extractNeighbours(cell, x, y, matrix);
        int greenis = countGreenis(cell);
        grid.put(cell, greenis);

        return Collections.unmodifiableMap(grid);
    }

    @Override
    public void clearCollection(Map<Cell, Integer> cells) {
        grid.clear();
    }

    private void extractNeighbours(Cell cell, int x, int y, int[][] matrix) {
        int x1 = cell.getX();//COL
        int y1 = cell.getY();//ROW

        if (isValidIndex(x1, y1, x, y)) {
            if (isValidIndex(x1 + 1, y1, x, y))
                cell.addNeighbour(factory.createCell(x1 + 1, y1, matrix[y1][x1 + 1]));

            if (isValidIndex(x1 - 1, y1, x, y))
                cell.addNeighbour(factory.createCell(x1 - 1, y1, matrix[y1][x1 - 1]));

            if (isValidIndex(x1, y1 + 1, x, y))
                cell.addNeighbour(factory.createCell(x1, y1 + 1, matrix[y1 + 1][x1]));

            if (isValidIndex(x1, y1 - 1, x, y))
                cell.addNeighbour(factory.createCell(x1, y1 - 1, matrix[y1 - 1][x1]));

            if (isValidIndex(x1 - 1, y1 + 1, x, y))
                cell.addNeighbour(factory.createCell(x1 - 1, y1 + 1, matrix[y1 + 1][x1 - 1]));

            if (isValidIndex(x1 + 1, y1 - 1, x, y))
                cell.addNeighbour(factory.createCell(x1 + 1, y1 - 1, matrix[y1 - 1][x1 + 1]));

            if (isValidIndex(x1 + 1, y1 + 1, x, y))
                cell.addNeighbour(factory.createCell(x1 + 1, y1 + 1, matrix[y1 + 1][x1 + 1]));

            if (isValidIndex(x1 - 1, y1 - 1, x, y))
                cell.addNeighbour(factory.createCell(x1 - 1, y1 - 1, matrix[y1 - 1][x1 - 1]));
        }
    }

    private static boolean isValidIndex(int x1, int y1, int x, int y) {
        return ((x1 >= 0 && x1 < x) && (y1 >= 0 && y1 < y));
    }

    private int countGreenis(Cell cell) {
        return ((int) cell.getNeighbours()
                .stream()
                .filter(c -> c.getClass().getSimpleName().equals("Green"))
                .count());
    }
}
