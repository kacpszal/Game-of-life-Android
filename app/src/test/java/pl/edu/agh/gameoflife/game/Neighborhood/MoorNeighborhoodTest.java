package pl.edu.agh.gameoflife.game.Neighborhood;

import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.CellFactory;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;
import pl.edu.agh.gameoflife.game.cell.SimpleCellFactory;
import pl.edu.agh.gameoflife.game.grid.EndlessGrid;
import pl.edu.agh.gameoflife.game.neighborhood.AbstractCellNeighborhood;
import pl.edu.agh.gameoflife.game.neighborhood.MooreNeighborhood;
import pl.edu.agh.gameoflife.test.RobolectricTest;

import static org.fest.assertions.api.Assertions.assertThat;

public class MoorNeighborhoodTest extends RobolectricTest {
    EndlessGrid<SimpleCell> grid;
    CellFactory<SimpleCell> cellFactory;
    AbstractCellNeighborhood neighborhood;

    @Before
    public void setup() {
        cellFactory = new SimpleCellFactory();
        grid = new EndlessGrid<>(10, 10, cellFactory);
    }

    @Test
    public void notifyNeighborsSmallRadius() {
        neighborhood = new MooreNeighborhood(grid, 1);
        Cell cell = new SimpleCell(2, 2, 1);
        grid.putCell(cellFactory.create(1, 1));
        grid.putCell(cellFactory.create(1, 2));
        grid.putCell(cellFactory.create(1, 3));
        grid.putCell(cellFactory.create(2, 1));
        grid.putCell(cellFactory.create(2, 3));
        grid.putCell(cellFactory.create(3, 1));
        grid.putCell(cellFactory.create(3, 2));
        grid.putCell(cellFactory.create(3, 3));

        neighborhood.notifyNeighbors(cell);

        assertThat(grid.getCell(1, 1).getNeighborCount()).isEqualTo(1);
        assertThat(grid.getCell(1, 2).getNeighborCount()).isEqualTo(1);
        assertThat(grid.getCell(1, 3).getNeighborCount()).isEqualTo(1);
        assertThat(grid.getCell(2, 1).getNeighborCount()).isEqualTo(1);
        assertThat(grid.getCell(2, 3).getNeighborCount()).isEqualTo(1);
        assertThat(grid.getCell(3, 1).getNeighborCount()).isEqualTo(1);
        assertThat(grid.getCell(3, 2).getNeighborCount()).isEqualTo(1);
        assertThat(grid.getCell(3, 3).getNeighborCount()).isEqualTo(1);
    }

    @Test
    public void notifyNeighborsBiggerRadius() {
        neighborhood = new MooreNeighborhood(grid, 3);
        Cell cell = new SimpleCell(5, 5, 1);

        grid.putCell(cellFactory.create(2, 5));
        grid.putCell(cellFactory.create(8, 5));
        grid.putCell(cellFactory.create(5, 2));
        grid.putCell(cellFactory.create(5, 8));

        neighborhood.notifyNeighbors(cell);

        assertThat(grid.getCell(2, 5).getNeighborCount()).isEqualTo(1);
        assertThat(grid.getCell(8, 5).getNeighborCount()).isEqualTo(1);
        assertThat(grid.getCell(5, 8).getNeighborCount()).isEqualTo(1);
        assertThat(grid.getCell(5, 2).getNeighborCount()).isEqualTo(1);
    }
}
