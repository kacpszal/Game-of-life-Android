package pl.edu.agh.gameoflife.game.structures;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;

public class SpaceshipStructure extends AbstractStructure {
    public SpaceshipStructure(int x, int y, int width, int height) {
        super(x, y, width, height);

        listOfStructure = new ArrayList<>();

        if (x + 7 <= width && y + 14 <= height) {
            listOfStructure.add(new SimpleCell(x + 1, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 2, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 5, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 6, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 3, y + 4, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 4, y + 4, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 3, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 4, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 2, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 5, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 7, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 7, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x, y + 9, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 7, y + 9, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 1, y + 10, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 2, y + 10, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 5, y + 10, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 6, y + 10, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 2, y + 11, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 3, y + 11, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 4, y + 11, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 5, y + 11, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 3, y + 13, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 4, y + 13, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 3, y + 14, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 4, y + 14, Cell.STATE_ALIVE));
        }
    }

    @Override
    public List<Cell> getListOfStructure() {
        return listOfStructure;
    }
}
