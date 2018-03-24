package pl.edu.agh.gameoflife.game.neighborhood;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.grid.Grid;

public abstract class AbstractCellNeighborhood implements CellNeighborhood {
    protected Grid grid;

    public AbstractCellNeighborhood(Grid grid) {
        this.grid = grid;
    }

    abstract public void notifyNeighbors(Cell cell);
}
