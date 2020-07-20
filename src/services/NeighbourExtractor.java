package services;

import models.cell.Cell;

import java.util.Map;

public interface NeighbourExtractor {

    Map<Cell, Integer> getNeighbours(int row, int col,int x, int y, int[][] matrix);

    void clearCollection(Map<Cell, Integer> cells);


}
