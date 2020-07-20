package core;

import factories.CellFactory;
import factories.CellFactoryImpl;
import models.cell.Cell;

import java.util.LinkedHashMap;
import java.util.Map;

public class GenerationServiceImpl implements GenerationService {

    private RulesController controller;
    private CellFactory cellFactory;
    private Map<Cell, Integer> cellCollection;

    public GenerationServiceImpl() {
        this.controller = new RulesControllerImpl();
        this.cellFactory = new CellFactoryImpl();
        this.cellCollection = new LinkedHashMap<>();
    }

    @Override
    public int[][] createNextGeneration(int x, int y, int[][] matrix) {

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int cellValue = matrix[row][col];

                Cell cell = cellFactory.createCell(col, row, cellValue);
                extractNeighbours(cell, x, y, matrix);
                int greenis = countGreenis(cell);
                cellCollection.put(cell, greenis);
            }
        }
        controller.applyRules(cellCollection, matrix);
        cellCollection.clear();

        return matrix;
    }

    private int countGreenis(Cell cell) {
        return ((int) cell.getNeighbours()
                .stream()
                .filter(c -> c.getClass().getSimpleName().equals("Green"))
                .count());
    }

    private void extractNeighbours(Cell cell, int x, int y, int[][] matrix) {
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
