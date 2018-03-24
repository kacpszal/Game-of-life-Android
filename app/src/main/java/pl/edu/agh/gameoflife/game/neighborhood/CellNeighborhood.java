package pl.edu.agh.gameoflife.game.neighborhood;

import pl.edu.agh.gameoflife.game.cell.Cell;

public interface CellNeighborhood {
    void notifyNeighbors(Cell cell);
}
