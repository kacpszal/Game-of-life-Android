package pl.edu.agh.gameoflife.game.automaton;

import com.squareup.otto.Subscribe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;
import pl.edu.agh.gameoflife.game.event.CellStateChange;
import pl.edu.agh.gameoflife.game.grid.Grid;
import pl.edu.agh.gameoflife.game.manager.GameParams;
import pl.edu.agh.gameoflife.test.RobolectricTest;
import pl.edu.agh.gameoflife.util.EventBus;

import static org.fest.assertions.api.Assertions.assertThat;

public class GameOfLifeAutomatonTest extends RobolectricTest {
    TestAutomaton automaton;
    List<CellStateChange> cellStateChanges;
    GameParams gameParams;

    @Before
    public void setup() {
        gameParams = new GameParams.Builder(null, 0).
                withGridSizeX(5).
                withGridSizeY(5).
                build();
        automaton = new TestAutomaton(gameParams);
        cellStateChanges = new ArrayList<>();
        EventBus.getInstance().register(this);
    }

    @After
    public void tearDown() {
        EventBus.getInstance().unregister(this);
    }

    @Test
    public void test4StepsShouldMoveGlider1UnitDiagonally() {
        automaton.setCurrentState(createGlider(automaton.getCurrentState(), 0));
        automaton.step(4);

        Grid<SimpleCell> currentState = automaton.getCurrentState();
        Grid<SimpleCell> expectedStateAfter4Steps = createGlider(automaton.getGridHandler().createNew(), 1);

        assertThat(currentState).isEqualTo(expectedStateAfter4Steps);
    }

    private Grid<SimpleCell> createGlider(Grid<SimpleCell> grid, int offset) {
        grid.getCell(0 + offset, 1 + offset).setState(Cell.STATE_ALIVE);
        grid.getCell(1 + offset, 2 + offset).setState(Cell.STATE_ALIVE);
        grid.getCell(2 + offset, 0 + offset).setState(Cell.STATE_ALIVE);
        grid.getCell(2 + offset, 1 + offset).setState(Cell.STATE_ALIVE);
        grid.getCell(2 + offset, 2 + offset).setState(Cell.STATE_ALIVE);

        return grid;
    }

    @Subscribe
    public void onEvent(CellStateChange cellStateChange) {
        cellStateChanges.add(cellStateChange);
    }

    @Test
    public void testCellStateListener() {
        automaton.setCurrentState(createGlider(automaton.getCurrentState(), 0));
        automaton.step();

        assertGliderCreationStateChangesCaptured();
        assertGliderStepStateChangesCaptured();
        assertThat(cellStateChanges.size()).isEqualTo(9);
    }

    protected void assertGliderCreationStateChangesCaptured() {
        assertThat(cellStateChanges.contains(new CellStateChange(new SimpleCell(0, 1, Cell.STATE_ALIVE)))).isEqualTo(true);
        assertThat(cellStateChanges.contains(new CellStateChange(new SimpleCell(1, 2, Cell.STATE_ALIVE)))).isEqualTo(true);
        assertThat(cellStateChanges.contains(new CellStateChange(new SimpleCell(2, 0, Cell.STATE_ALIVE)))).isEqualTo(true);
        assertThat(cellStateChanges.contains(new CellStateChange(new SimpleCell(2, 1, Cell.STATE_ALIVE)))).isEqualTo(true);
        assertThat(cellStateChanges.contains(new CellStateChange(new SimpleCell(2, 2, Cell.STATE_ALIVE)))).isEqualTo(true);
    }

    private void assertGliderStepStateChangesCaptured() {
        assertThat(cellStateChanges.contains(new CellStateChange(new SimpleCell(1, 0, Cell.STATE_ALIVE)))).isEqualTo(true);
        assertThat(cellStateChanges.contains(new CellStateChange(new SimpleCell(2, 0, Cell.STATE_DEAD)))).isEqualTo(true);
        assertThat(cellStateChanges.contains(new CellStateChange(new SimpleCell(0, 1, Cell.STATE_DEAD)))).isEqualTo(true);
        assertThat(cellStateChanges.contains(new CellStateChange(new SimpleCell(3, 1, Cell.STATE_ALIVE)))).isEqualTo(true);
    }

    private static class TestAutomaton extends GameOfLifeAutomaton {
        public TestAutomaton(GameParams gameParams) {
            super(gameParams);
        }

        public final void setCurrentState(Grid<SimpleCell> grid) {
            gridHandler.setCurrent(grid);
        }
    }
}
