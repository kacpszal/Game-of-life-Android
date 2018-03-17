package pl.edu.agh.gameoflife.game.transformer;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.grid.Grid;
import pl.edu.agh.gameoflife.game.rule.Rule;

public interface GridTransformer<T extends Cell> {
    public void transform(Grid<T> grid, Rule<T> rule);
}
