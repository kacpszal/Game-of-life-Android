package pl.edu.agh.gameoflife.game.grid;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.CellFactory;
import pl.edu.agh.gameoflife.game.manager.GameParams;

public class EndlessGridHandler<T extends Cell> implements GridHandler<T> {
    private final int x;
    private final int y;
    private final CellFactory<T> cellFactory;
    private Grid<T> currentGrid;
    private GameParams gameParams;

    public EndlessGridHandler(int x, int y, GameParams gameParams, CellFactory<T> cellFactory) {
        this.x = x;
        this.y = y;
        this.cellFactory = cellFactory;
        this.gameParams = gameParams;
        currentGrid = createNew();
    }

    @Override
    public Grid<T> getCurrent() {
        return currentGrid;
    }

    @Override
    public Grid<T> createNew() {
        return new EndlessGrid<T>(x, y, gameParams,cellFactory);
    }

    @Override
    public void setCurrent(Grid<T> grid) {
        if (grid instanceof EndlessGrid) {
            currentGrid = grid;

        } else {
            currentGrid = new EndlessGrid<T>(grid, cellFactory, gameParams);
        }
    }
}
