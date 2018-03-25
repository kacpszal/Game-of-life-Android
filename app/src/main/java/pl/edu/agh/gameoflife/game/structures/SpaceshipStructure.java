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
            listOfStructure.add(new SimpleCell(x + 1, y + 3, 1));
            listOfStructure.add(new SimpleCell(x + 2, y + 3, 1));
            listOfStructure.add(new SimpleCell(x + 5, y + 3, 1));
            listOfStructure.add(new SimpleCell(x + 6, y + 3, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 4, 1));
            listOfStructure.add(new SimpleCell(x + 4, y + 4, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 5, 1));
            listOfStructure.add(new SimpleCell(x + 4, y + 5, 1));
            listOfStructure.add(new SimpleCell(x, y + 6, 1));
            listOfStructure.add(new SimpleCell(x + 2, y + 6, 1));
            listOfStructure.add(new SimpleCell(x + 5, y + 6, 1));
            listOfStructure.add(new SimpleCell(x + 7, y + 6, 1));
            listOfStructure.add(new SimpleCell(x, y + 7, 1));
            listOfStructure.add(new SimpleCell(x + 7, y + 7, 1));
            listOfStructure.add(new SimpleCell(x, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 7, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 1, y + 10, 1));
            listOfStructure.add(new SimpleCell(x + 2, y + 10, 1));
            listOfStructure.add(new SimpleCell(x + 5, y + 10, 1));
            listOfStructure.add(new SimpleCell(x + 6, y + 10, 1));
            listOfStructure.add(new SimpleCell(x + 2, y + 11, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 11, 1));
            listOfStructure.add(new SimpleCell(x + 4, y + 11, 1));
            listOfStructure.add(new SimpleCell(x + 5, y + 11, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 13, 1));
            listOfStructure.add(new SimpleCell(x + 4, y + 13, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 14, 1));
            listOfStructure.add(new SimpleCell(x + 4, y + 14, 1));
        }
    }

    @Override
    public List<Cell> getListOfStructure() {
        return listOfStructure;
    }
}
