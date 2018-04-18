package pl.edu.agh.gameoflife.game.neighborhood;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.grid.Grid;
import pl.edu.agh.gameoflife.game.manager.GameParams;

public class VonNeumannNeighborhood extends AbstractCellNeighborhood {
    public VonNeumannNeighborhood(Grid grid, GameParams gameParams) {
        super(grid, gameParams);
    }

    @Override
    public void notifyNeighbors(Cell cell) {
        for (int j = -gameParams.getRadius(); j <= gameParams.getRadius(); ++j) {
            if(j != 0) {
                Cell neighbor = grid.getCell(cell.getX(), cell.getY() + j);
                if(neighbor != null) {
                    neighbor.onNeighborStateChange(cell.getState());
                }
            }
        }
        for (int i = -gameParams.getRadius(); i <= gameParams.getRadius(); ++i) {
            if(i != 0) {
                Cell neighbor = grid.getCell(cell.getX() + i, cell.getY());
                if(neighbor != null) {
                    neighbor.onNeighborStateChange(cell.getState());
                }
            }
        }
    }
}
