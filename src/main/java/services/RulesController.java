package services;

import models.cell.Cell;

import java.util.Map;

public interface RulesController {

    void applyRules(Map<Cell, Integer> cells, int[][] matrix);

}
