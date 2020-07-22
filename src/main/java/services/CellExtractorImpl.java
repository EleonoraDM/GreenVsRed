package services;

import common.Validator;
import factories.CellFactory;
import factories.CellFactoryImpl;
import models.cell.Cell;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CellExtractorImpl implements CellExtractor {
    private CellFactory factory;
    private Map<Cell, Integer> cells;

    public CellExtractorImpl() {
        this.factory = new CellFactoryImpl();
        this.cells = new LinkedHashMap<>();
    }

    @Override
    public Map<Cell, Integer> getCells(int row, int col, int x, int y, int[][] matrix) {
        int cellValue = matrix[row][col];

        Cell cell = factory.createCell(col, row, cellValue);
        extractNeighbours(cell, x, y, matrix);
        int greenis = countGreenis(cell);
        cells.put(cell, greenis);

        return Collections.unmodifiableMap(cells);
    }

    @Override
    public void clearCollection(Map<Cell, Integer> cells) {
        this.cells.clear();
    }

    private void extractNeighbours(Cell cell, int x, int y, int[][] matrix) {
        int x1 = cell.getX();//COL
        int y1 = cell.getY();//ROW

        if (Validator.isValidIndex(x1, y1, x, y)) {
            if (Validator.isValidIndex(x1 + 1, y1, x, y))
                cell.addNeighbour(factory.createCell(x1 + 1, y1, matrix[y1][x1 + 1]));

            if (Validator.isValidIndex(x1 - 1, y1, x, y))
                cell.addNeighbour(factory.createCell(x1 - 1, y1, matrix[y1][x1 - 1]));

            if (Validator.isValidIndex(x1, y1 + 1, x, y))
                cell.addNeighbour(factory.createCell(x1, y1 + 1, matrix[y1 + 1][x1]));

            if (Validator.isValidIndex(x1, y1 - 1, x, y))
                cell.addNeighbour(factory.createCell(x1, y1 - 1, matrix[y1 - 1][x1]));

            if (Validator.isValidIndex(x1 - 1, y1 + 1, x, y))
                cell.addNeighbour(factory.createCell(x1 - 1, y1 + 1, matrix[y1 + 1][x1 - 1]));

            if (Validator.isValidIndex(x1 + 1, y1 - 1, x, y))
                cell.addNeighbour(factory.createCell(x1 + 1, y1 - 1, matrix[y1 - 1][x1 + 1]));

            if (Validator.isValidIndex(x1 + 1, y1 + 1, x, y))
                cell.addNeighbour(factory.createCell(x1 + 1, y1 + 1, matrix[y1 + 1][x1 + 1]));

            if (Validator.isValidIndex(x1 - 1, y1 - 1, x, y))
                cell.addNeighbour(factory.createCell(x1 - 1, y1 - 1, matrix[y1 - 1][x1 - 1]));
        }
    }

    private int countGreenis(Cell cell) {
        return ((int) cell.getNeighbours()
                .stream()
                .filter(c -> c.getClass().getSimpleName().equals("Green"))
                .count());
    }
}
