package factories;

import models.cell.Cell;
import services.CellExtractor;
import services.CellExtractorImpl;
import services.RulesController;
import services.RulesControllerImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class GenerationFactoryImpl implements GenerationFactory {

    private RulesController controller;
    private CellExtractor creator;

    public GenerationFactoryImpl() {
        this.controller = new RulesControllerImpl();
        this.creator = new CellExtractorImpl();
    }

    @Override
    public int[][] createNextGeneration(int x, int y, int[][] matrix) {
        Map<Cell, Integer> cells = new LinkedHashMap<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {

               cells = creator.getCells(row, col, x, y, matrix);
            }
        }
        controller.applyRules(cells, matrix);
        creator.clearCollection(cells);

        return matrix;
    }


}
