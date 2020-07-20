package models.cell;

import java.util.List;

public interface Cell {

    int getX();

    int getY();

    void addNeighbour(Cell cell);

    List<Cell> getNeighbours();
}
