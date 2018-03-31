package pl.edu.agh.gameoflife.game.structures;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;

public class GunStructure extends AbstractStructure {

    public GunStructure(int x, int y, int width, int height) {
        super(x, y, width, height);

        listOfStructure = new ArrayList<>();

        if (x + 38 <= width && y + 8 < height) {
            listOfStructure.add(new SimpleCell(x, y + 4, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 1, y + 4, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 1, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 10, y + 2, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 10, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 10, y + 4, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 11, y + 1, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 11, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 12, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 13, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 12, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 13, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 14, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 15, y + 1, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 15, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 16, y + 4, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 16, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 16, y + 2, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 17, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 20, y + 4, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 20, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 20, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 21, y + 4, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 21, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 21, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 22, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 22, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 24, y + 3, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 24, y + 2, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 24, y + 7, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 24, y + 8, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 34, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 34, y + 6, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 35, y + 5, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 35, y + 6, Cell.STATE_ALIVE));
        }
    }

    @Override
    public List<Cell> getListOfStructure() {
        return listOfStructure;
    }
}
