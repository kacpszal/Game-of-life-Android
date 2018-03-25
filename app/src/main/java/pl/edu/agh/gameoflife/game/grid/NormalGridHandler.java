package pl.edu.agh.gameoflife.game.grid;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.CellFactory;

public class NormalGridHandler <T extends Cell> implements GridHandler<T> {
    private final int x;
    private final int y;
    private final CellFactory<T> cellFactory;
    Grid<T> currentGrid;

    public NormalGridHandler(int x, int y, CellFactory<T> cellFactory) {
        this.x = x;
        this.y = y;
        this.cellFactory = cellFactory;
        currentGrid = createNew();
    }

    @Override
    public Grid<T> getCurrent() {
        return currentGrid;
    }

    @Override
    public Grid<T> createNew() {
        return new NormalGrid<T>(x, y, cellFactory);
    }

    @Override
    public void setCurrent(Grid<T> grid) {
        if (grid instanceof NormalGrid) {
            currentGrid = grid;

        } else {
            currentGrid = new NormalGrid<T>(grid, cellFactory);
        }
    }
}
