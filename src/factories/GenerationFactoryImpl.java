package factories;

import models.cell.Cell;
import services.GridCreator;
import services.GridCreatorImpl;
import services.RulesController;
import services.RulesControllerImpl;

import java.util.LinkedHashMap;
import java.util.Map;

public class GenerationFactoryImpl implements GenerationFactory {

    private RulesController controller;
    private GridCreator creator;

    public GenerationFactoryImpl() {
        this.controller = new RulesControllerImpl();
        this.creator = new GridCreatorImpl();
    }

    @Override
    public int[][] createNextGeneration(int x, int y, int[][] matrix) {
        Map<Cell, Integer> neighbours = new LinkedHashMap<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {

               neighbours = creator.getCells(row, col, x, y, matrix);
            }
        }
        controller.applyRules(neighbours, matrix);
        creator.clearCollection(neighbours);

        return matrix;
    }


}
