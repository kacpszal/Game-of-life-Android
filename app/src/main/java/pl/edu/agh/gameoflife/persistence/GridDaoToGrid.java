package pl.edu.agh.gameoflife.persistence;

import pl.edu.agh.gameoflife.game.cell.CellFromGridDaoFactory;
import pl.edu.agh.gameoflife.game.grid.NormalGrid;
import pl.edu.agh.gameoflife.game.grid.Grid;
import pl.edu.agh.gameoflife.game.manager.GameParams;

/**
 * Created by grzegorz on 11/21/17.
 */

public class GridDaoToGrid {

    public static Grid parse(GridDao gridDao, GameParams gameParams) {
        gameParams.setCellRule(gridDao.rule);
        gameParams.setCellNeighborhood(gridDao.neighborhood);
        gameParams.setRadius(gridDao.neighborhoodRadius);
        gameParams.setMapWrapping(gridDao.wrapping);
        gameParams.setStructure(gridDao.structures);
        gameParams.setSpeedAnimation(gridDao.speedAnimation);
        gameParams.setStepAnimation(gridDao.stepAnimation);
        gameParams.setSlowerFaster(gridDao.slowerFaster);
        return new NormalGrid(gridDao.getSizeX(), gridDao.getSizeY(), gameParams, new CellFromGridDaoFactory(gridDao));
    }
}
