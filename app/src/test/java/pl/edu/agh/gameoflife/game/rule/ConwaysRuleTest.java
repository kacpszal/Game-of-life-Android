package pl.edu.agh.gameoflife.game.rule;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.CellFactory;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;
import pl.edu.agh.gameoflife.game.cell.SimpleCellFactory;
import pl.edu.agh.gameoflife.game.grid.EndlessGrid;
import pl.edu.agh.gameoflife.game.grid.Grid;
import pl.edu.agh.gameoflife.game.transformer.GridTransformer;
import pl.edu.agh.gameoflife.game.transformer.ThreadedGridTransformer;
import pl.edu.agh.gameoflife.test.RobolectricTest;

import static org.fest.assertions.api.Assertions.assertThat;

public class ConwaysRuleTest extends RobolectricTest {
    CellFactory<SimpleCell> cellFactory;
    GridTransformer<SimpleCell> transformer;
    Rule<SimpleCell> rule;

    @Before
    public void setup() {
        cellFactory = new SimpleCellFactory();
        transformer = new ThreadedGridTransformer<>();
        rule = new ConwaysRule();
    }

    @Test
    public void testAllDeadCellsRemainSo() {
        Grid<SimpleCell> grid = createEmptyGrid();
        transform(grid);
        Grid<SimpleCell> expected = createEmptyGrid();

        testEqual(grid, expected);
    }

    @Test
    public void testLonelyDies() {
        Grid<SimpleCell> grid = createEmptyGrid();
        grid.getCell(1, 1).setState(Cell.STATE_ALIVE);
        transform(grid);
        Grid<SimpleCell> expected = createEmptyGrid();

        testEqual(grid, expected);
    }

    @Test
    public void testSimpleOscillator() {
        Grid<SimpleCell> grid = horizontalOscillator();
        transform(grid);
        Grid<SimpleCell> expected = verticalOscillator();

        testEqual(grid, expected);
    }

    private void transform(Grid<SimpleCell> grid) {
        transformer.transform(grid, rule);
    }

    private Grid<SimpleCell> createEmptyGrid() {
        return new EndlessGrid<>(5, 5, cellFactory);
    }

    private Grid<SimpleCell> horizontalOscillator() {
        Grid<SimpleCell> grid = createEmptyGrid();
        grid.getCell(1, 2).setState(Cell.STATE_ALIVE);
        grid.getCell(2, 2).setState(Cell.STATE_ALIVE);
        grid.getCell(3, 2).setState(Cell.STATE_ALIVE);

        return grid;
    }

    private Grid<SimpleCell> verticalOscillator() {
        Grid<SimpleCell> grid = createEmptyGrid();
        grid.getCell(2, 1).setState(Cell.STATE_ALIVE);
        grid.getCell(2, 2).setState(Cell.STATE_ALIVE);
        grid.getCell(2, 3).setState(Cell.STATE_ALIVE);

        return grid;
    }

    private void testEqual(Grid<SimpleCell> grid1, Grid<SimpleCell> grid2) {
        assertThat(grid1).isEqualTo(grid2);
    }
}
