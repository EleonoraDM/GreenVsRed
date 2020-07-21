package services;

import models.cell.Cell;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RulesControllerImpl implements RulesController {
    private final List<Integer> RED_TO_GREEN_RULE = Arrays.asList(3, 6);
    private final List<Integer> GREEN_TO_RED_RULE = Arrays.asList(0, 1, 4, 5, 7, 8);

    @Override
    public void applyRules(Map<Cell, Integer> cellCollection, int[][] matrix) {

        for (Map.Entry<Cell, Integer> entry : cellCollection.entrySet()) {

            Cell cell = entry.getKey();
            int greenis = entry.getValue();
            int col = cell.getX();
            int row = cell.getY();

            if (cell.getClass().getSimpleName().equals("Green")
                    && GREEN_TO_RED_RULE.contains(greenis)) {
                matrix[row][col] = 0;
            }

            if (cell.getClass().getSimpleName().equals("Red")
                    && RED_TO_GREEN_RULE.contains(greenis)) {
                matrix[row][col] = 1;
            }
        }
    }
}
