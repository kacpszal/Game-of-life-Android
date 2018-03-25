package pl.edu.agh.gameoflife.game.neighborhood;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.grid.Grid;

public class MooreNeighborhood extends AbstractCellNeighborhood {
    public MooreNeighborhood(Grid grid) {
        super(grid);
    }

    public MooreNeighborhood(Grid grid, int radius) {
        super(grid, radius);
    }

    @Override
    public void notifyNeighbors(Cell cell) {
        for (int j = -radius; j <= radius; ++j) {
            for (int i = -radius; i <= radius; ++i) {
                if (j != 0 || i != 0) {
                    Cell neighbor = grid.getCell(cell.getX() + i, cell.getY() + j);
                    if(neighbor != null) {
                        neighbor.onNeighborStateChange(cell.getState());
                    }
                }
            }
        }
    }
}
