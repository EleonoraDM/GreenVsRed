package models.cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CellImpl implements Cell {
    private int x;
    private int y;
    private List<Cell> neighbours;

    protected CellImpl(int x, int y) {
        this.x = x;
        this.y = y;
        this.neighbours = new ArrayList<>();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public void addNeighbour(Cell cell) {
        this.neighbours.add(cell);
    }

    @Override
    public List<Cell> getNeighbours() {
        return Collections.unmodifiableList(this.neighbours);
    }


}
