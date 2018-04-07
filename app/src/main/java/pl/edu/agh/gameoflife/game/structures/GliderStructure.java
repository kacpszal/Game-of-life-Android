package pl.edu.agh.gameoflife.game.structures;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;

public class GliderStructure extends AbstractStructure {
    public GliderStructure(int x, int y, int width, int height) {
        super(x, y, width, height);

        listOfStructure = new ArrayList<>();

        if (x + 2 <= width && y + 2 <= height) {
            listOfStructure.add(new SimpleCell(x, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 1, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 2, y, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x, y + 1, Cell.STATE_ALIVE));
            listOfStructure.add(new SimpleCell(x + 1, y + 2, Cell.STATE_ALIVE));
        }
    }

    @Override
    public List<Cell> getListOfStructure() {
        return listOfStructure;
    }
}
