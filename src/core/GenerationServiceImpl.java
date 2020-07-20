package core;

import models.cell.Cell;
import services.NeighbourExtractor;
import services.NeighbourExtractorImpl;
import services.RulesController;
import services.RulesControllerImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class GenerationServiceImpl implements GenerationService {

    private RulesController controller;
    private NeighbourExtractor extractor;

    public GenerationServiceImpl() {
        this.controller = new RulesControllerImpl();
        this.extractor = new NeighbourExtractorImpl();
    }

    @Override
    public int[][] createNextGeneration(int x, int y, int[][] matrix) {
        Map<Cell, Integer> neighbours = new LinkedHashMap<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {

               neighbours = extractor.getNeighbours(row, col, x, y, matrix);
            }
        }
        controller.applyRules(neighbours, matrix);
        extractor.clearCollection(neighbours);

        return matrix;
    }


}
