package factories;

import models.cell.Cell;
import models.cell.Green;
import models.cell.Red;

public class CellFactoryImpl implements CellFactory {

    @Override
    public Cell createCell(int x, int y, int cellValue) {
        Cell cell = null;

        if (cellValue == 0) {
            cell = new Red(x, y);

        } else if (cellValue == 1) {
            cell = new Green(x, y);

        } else {
            throw new IllegalArgumentException("Invalid cell type!");
        }
        return cell;
    }
}
