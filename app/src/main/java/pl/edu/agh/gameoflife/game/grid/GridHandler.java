package pl.edu.agh.gameoflife.game.grid;

import pl.edu.agh.gameoflife.game.cell.Cell;

public interface GridHandler<T extends Cell> {
    Grid<T> getCurrent();
    Grid<T> createNew();
    void setCurrent(Grid<T> grid);
}
