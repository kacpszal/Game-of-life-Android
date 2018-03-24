package pl.edu.agh.gameoflife.game.neighborhood;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.grid.Grid;

public class VonNeumannNeighborhood extends AbstractCellNeighborhood {
    public VonNeumannNeighborhood(Grid grid) {
        super(grid);
    }

    public VonNeumannNeighborhood(Grid grid, int radius) {
        super(grid, radius);
    }

    @Override
    public void notifyNeighbors(Cell cell) {
        for (int j = -radius; j <= radius; ++j) {
            if(j != 0) {
                Cell neighbor = grid.getCell(cell.getX(), cell.getY() + j);
                neighbor.onNeighborStateChange(cell.getState());
            }
        }
        for (int i = -radius; i <= radius; ++i) {
            if(i != 0) {
                Cell neighbor = grid.getCell(cell.getX() + i, cell.getY());
                neighbor.onNeighborStateChange(cell.getState());
            }
        }
    }
}
