package pl.edu.agh.gameoflife.game.rule;

import java.io.Serializable;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.grid.Grid;

public interface Rule<T extends Cell> extends Serializable {
    int apply(Grid<T> grid, int x, int y);
    String toString();
}
