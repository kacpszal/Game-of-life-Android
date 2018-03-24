package pl.edu.agh.gameoflife.game.neighborhood;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.grid.Grid;

public abstract class AbstractCellNeighborhood implements CellNeighborhood {
    protected Grid grid;
    protected int radius;

    public AbstractCellNeighborhood(Grid grid) {
        this.grid = grid;
        radius = 1;
    }

    public AbstractCellNeighborhood(Grid grid, int radius) {
        this.grid = grid;
        this.radius = radius;
    }

    @Override
    abstract public void notifyNeighbors(Cell cell);
}
