package pl.edu.agh.gameoflife.persistence;

import pl.edu.agh.gameoflife.game.cell.CellFromGridDaoFactory;
import pl.edu.agh.gameoflife.game.grid.EndlessGrid;
import pl.edu.agh.gameoflife.game.grid.Grid;

/**
 * Created by grzegorz on 11/21/17.
 */

public class GridDaoToGrid {

    public static Grid parse(GridDao gridDao) {
        return new EndlessGrid(gridDao.getSizeX(), gridDao.getSizeY(), new CellFromGridDaoFactory(gridDao));
    }
}
