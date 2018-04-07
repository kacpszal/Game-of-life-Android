package pl.edu.agh.gameoflife.game.structures;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;

public class CrabStructure extends AbstractStructure {
    public CrabStructure(int x, int y, int width, int height) {
        super(x, y, width, height);

        listOfStructure = new ArrayList<>();

        if (x + 15 <= width && y + 10 <= height) {
            listOfStructure.add(new SimpleCell(x + 1, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 14, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 1, y + 1, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 14, y + 1, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x, y + 2, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 2, y + 2, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 13, y + 2, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 15, y + 2, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 1, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 14, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 1, y + 4, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 14, y + 4, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 2, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 6, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 7, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 8, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 9, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 13, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 6, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 7, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 8, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 9, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 2, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 3, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 4, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 5, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 10, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 11, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 12, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 13, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 4, y + 9, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 11, y + 9, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 5, y + 10, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 6, y + 10, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 9, y + 10, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 10, y + 10, Cell.STATE_ALIVE));
        }
    }

    @Override
    public List<Cell> getListOfStructure() {
        return listOfStructure;
    }
}
