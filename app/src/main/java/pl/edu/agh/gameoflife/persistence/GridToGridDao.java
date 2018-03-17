package pl.edu.agh.gameoflife.persistence;

import java.util.Date;

import pl.edu.agh.gameoflife.game.grid.Grid;

/**
 * Created by grzegorz on 11/21/17.
 */

public class GridToGridDao {

    public static GridDao parse(Grid grid) {
        GridDao gridDao = new GridDao();
        gridDao.setCellsState(new int[grid.getSizeY()][grid.getSizeX()]);
        gridDao.setSizeX(grid.getSizeX());
        gridDao.setSizeY(grid.getSizeY());
        gridDao.setDate(new Date());

        int[][] cells = gridDao.getCellsState();
        for (int i = 0; i < grid.getSizeY(); i++) {
            for (int j = 0; j < grid.getSizeX(); j++) {
                cells[i][j] = grid.getCell(j, i).getState();
            }
        }

        return gridDao;
    }
}
