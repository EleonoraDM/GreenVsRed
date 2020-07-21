package factories;

import models.cell.Cell;

public interface CellFactory {

    Cell createCell(int x, int y, int value);
}
