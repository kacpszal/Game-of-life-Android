package pl.edu.agh.gameoflife.game.structures;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;

public class PenthadecathlonStructure extends AbstractStructure {
    public PenthadecathlonStructure(int x, int y, int width, int height) {
        super(x, y, width, height);

        listOfStructure = new ArrayList<>();

        if (x + 10 <= width ) {
            listOfStructure.add(new SimpleCell(x, y, 1));
            listOfStructure.add(new SimpleCell(x + 1, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 2, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 3, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 4, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 5, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 6, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 7, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 8, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 9, y, Cell.STATE_ALIVE));
        }
    }

    @Override
    public List<Cell> getListOfStructure() {
        return listOfStructure;
    }
}
