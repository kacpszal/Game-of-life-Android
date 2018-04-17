package pl.edu.agh.gameoflife.game.grid;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.CellFactory;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;
import pl.edu.agh.gameoflife.game.cell.SimpleCellFactory;
import pl.edu.agh.gameoflife.game.manager.GameParams;
import pl.edu.agh.gameoflife.test.RobolectricTest;

import static org.fest.assertions.api.Assertions.*;

public class NormalGridTest extends RobolectricTest {
    NormalGrid<SimpleCell> grid;
    GameParams gameParams;
    CellFactory<SimpleCell> cellFactory;

    @Before
    public void setup() {
        cellFactory = new SimpleCellFactory();
        gameParams = new GameParams.Builder(null, 0).build();
        grid = new NormalGrid<>(3, 6, gameParams, cellFactory);
    }

    @Test
    public void testAllCellsAreDeadByDefault() {
        for (int j = 0; j < grid.getSizeY(); j++) {
            for (int i = 0; i < grid.getSizeX(); i++) {
                assertThat(grid.getCell(i, j).isAlive()).isEqualTo(false);
            }
        }
    }

    @Test
    public void testGetSizeX() {
        assertThat(grid.getSizeX()).isEqualTo(3);
    }

    @Test
    public void testGetSizeY() {
        assertThat(grid.getSizeY()).isEqualTo(6);
    }

    @Test
    public void testNormalizeX() {
        assertThat(grid.normalizeX(-5)).isEqualTo(1);
    }

    @Test
    public void testNormalizeY() {
        assertThat(grid.normalizeY(7)).isEqualTo(1);
    }

    @Test
    public void testPutCell() {
        SimpleCell cell = cellFactory.create(1, 1);
        cell.setState(Cell.STATE_ALIVE);
        grid.putCell(cell);
        assertThat(grid.getCell(1, 1).isAlive()).isTrue();
    }

    @Test
    public void testEquals() {
        NormalGrid other = null;

        assertThat(grid).isEqualTo(grid);
        assertThat(grid.equals(other)).isFalse();
        assertThat(grid.equals(this)).isFalse();

        other = new NormalGrid<>(1, 6, gameParams, cellFactory);
        assertThat(grid).isNotEqualTo(other);

        other = new NormalGrid<>(3, 1, gameParams, cellFactory);
        assertThat(grid).isNotEqualTo(other);

        other = new NormalGrid<>(3, 6, gameParams, cellFactory);
        other.getCell(1, 1).setState(Cell.STATE_ALIVE);
        assertThat(grid).isNotEqualTo(other);

        grid.getCell(1, 1).setState(Cell.STATE_ALIVE);
        assertThat(grid).isEqualTo(other);
    }
}
