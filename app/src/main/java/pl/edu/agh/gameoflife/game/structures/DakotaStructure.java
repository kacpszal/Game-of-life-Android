package pl.edu.agh.gameoflife.game.structures;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;

public class DakotaStructure extends AbstractStructure {
    public DakotaStructure(int x, int y, int width, int height) {
        super(x, y, width, height);

        listOfStructure = new ArrayList<>();

        if (x + 4 <= width && y + 3 <= height) {
            listOfStructure.add(new SimpleCell(x + 1, y, 1));
            listOfStructure.add(new SimpleCell(x + 4, y, 1));
            listOfStructure.add(new SimpleCell(x, y + 1, 1));
            listOfStructure.add(new SimpleCell(x, y + 2, 1));
            listOfStructure.add(new SimpleCell(x + 4, y + 2, 1));
            listOfStructure.add(new SimpleCell(x, y + 3, 1));
            listOfStructure.add(new SimpleCell(x + 1, y + 3, 1));
            listOfStructure.add(new SimpleCell(x + 2, y + 3, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 3, 1));
        }
    }

    @Override
    public List<Cell> getListOfStructure() {
        return listOfStructure;
    }
}
