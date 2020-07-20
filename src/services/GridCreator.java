package services;

import models.cell.Cell;

import java.util.Map;

public interface GridCreator {

    Map<Cell, Integer> getCells(int row, int col,int x, int y, int[][] matrix);

    void clearCollection(Map<Cell, Integer> cells);


}
