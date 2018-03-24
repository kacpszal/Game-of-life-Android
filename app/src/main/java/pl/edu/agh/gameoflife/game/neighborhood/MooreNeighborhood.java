package pl.edu.agh.gameoflife.game.neighborhood;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.grid.Grid;

public class MooreNeighborhood extends AbstractCellNeighborhood {
    public MooreNeighborhood(Grid grid) {
        super(grid);
    }

    @Override
    public void notifyNeighbors(Cell cell) {
        for (int j = -1; j <= 1; ++j) {
            for (int i = -1; i <= 1; ++i) {
                if (j != 0 || i != 0) {
                    Cell neighbor = grid.getCell(cell.getX() + i, cell.getY() + j);
                    neighbor.onNeighborStateChange(cell.getState());
                }
            }
        }
    }
}
