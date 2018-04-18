package pl.edu.agh.gameoflife.game.neighborhood;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.grid.Grid;
import pl.edu.agh.gameoflife.game.manager.GameParams;

public abstract class AbstractCellNeighborhood implements CellNeighborhood {
    protected Grid grid;
    protected GameParams gameParams;

    public AbstractCellNeighborhood(Grid grid, GameParams gameParams) {
        this.grid = grid;
        this.gameParams = gameParams;
    }

    @Override
    abstract public void notifyNeighbors(Cell cell);
}
