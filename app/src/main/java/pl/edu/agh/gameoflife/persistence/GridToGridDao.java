package pl.edu.agh.gameoflife.persistence;

import pl.edu.agh.gameoflife.game.grid.Grid;
import pl.edu.agh.gameoflife.game.manager.GameParams;

/**
 * Created by grzegorz on 11/21/17.
 */

public class GridToGridDao {

    public static GridDao parse(Grid grid, String saveText, GameParams params) {
        GridDao gridDao = new GridDao();
        gridDao.setCellsState(new int[grid.getSizeY()][grid.getSizeX()]);
        gridDao.setSizeX(grid.getSizeX());
        gridDao.setSizeY(grid.getSizeY());
        gridDao.setSaveText(saveText);
        gridDao.rule = params.getCellRule();
        gridDao.neighborhood = params.getCellNeighborhood();
        gridDao.neighborhoodRadius = params.getRadius();
        gridDao.wrapping = params.getMapWrapping();
        gridDao.structures = params.getStructure();
        gridDao.speedAnimation = params.getSpeedAnimation();
        gridDao.stepAnimation = params.getStepAnimation();
        gridDao.slowerFaster = params.getSlowerFaster();

        int[][] cells = gridDao.getCellsState();
        for (int i = 0; i < grid.getSizeY(); i++) {
            for (int j = 0; j < grid.getSizeX(); j++) {
                cells[i][j] = grid.getCell(j, i).getState();
            }
        }

        return gridDao;
    }
}
