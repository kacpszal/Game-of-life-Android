package pl.edu.agh.gameoflife.game.grid;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.SimpleCellFactory;
import pl.edu.agh.gameoflife.game.manager.GameParams;
import pl.edu.agh.gameoflife.test.RobolectricTest;

import static org.fest.assertions.api.Assertions.assertThat;

public class NormalGridHandlerTest extends RobolectricTest {
    NormalGridHandler gridHandler;
    GameParams gameParams;
    private SimpleCellFactory cellFactory;

    @Before
    public void setup() {
        cellFactory = new SimpleCellFactory();
        gameParams = new GameParams.Builder(null, 0).build();
        gridHandler = new NormalGridHandler(3, 3, gameParams, cellFactory);
    }

    @Test
    public void testGetCurrentAfterCreation() {
        assertThat(gridHandler.getCurrent()).isEqualTo(new NormalGrid(3, 3, gameParams,cellFactory));
    }

    @Test
    public void testCreateNew() {
        assertThat(gridHandler.createNew()).isEqualTo(new NormalGrid(3, 3, gameParams,cellFactory));
    }

    @Test
    public void testSetCurrent() {
        Grid newGrid = gridHandler.createNew();
        newGrid.getCell(1, 1).setState(Cell.STATE_ALIVE);
        gridHandler.setCurrent(newGrid);

        assertThat(gridHandler.getCurrent()).isEqualTo(newGrid);
    }
}
