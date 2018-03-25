package pl.edu.agh.gameoflife.game.structures;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;

public class FountainStructure extends AbstractStructure {
    public FountainStructure(int x, int y, int width, int height) {
        super(x, y, width, height);

        listOfStructure = new ArrayList<>();

        if (x + 18 <= width && y + 14 <= height) {
            listOfStructure.add(new SimpleCell(x + 9, y, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 2, 1));
            listOfStructure.add(new SimpleCell(x + 4, y + 2, 1));
            listOfStructure.add(new SimpleCell(x + 6, y + 2, 1));
            listOfStructure.add(new SimpleCell(x + 12, y + 2, 1));
            listOfStructure.add(new SimpleCell(x + 14, y + 2, 1));
            listOfStructure.add(new SimpleCell(x + 15, y + 2, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 3, 1));
            listOfStructure.add(new SimpleCell(x + 9, y + 3, 1));
            listOfStructure.add(new SimpleCell(x + 15, y + 3, 1));
            listOfStructure.add(new SimpleCell(x + 4, y + 4, 1));
            listOfStructure.add(new SimpleCell(x + 5, y + 4, 1));
            listOfStructure.add(new SimpleCell(x + 7, y + 4, 1));
            listOfStructure.add(new SimpleCell(x + 8, y + 4, 1));
            listOfStructure.add(new SimpleCell(x + 10, y + 4, 1));
            listOfStructure.add(new SimpleCell(x + 11, y + 4, 1));
            listOfStructure.add(new SimpleCell(x + 13, y + 4, 1));
            listOfStructure.add(new SimpleCell(x + 14, y + 4, 1));
            listOfStructure.add(new SimpleCell(x + 6, y + 6, 1));
            listOfStructure.add(new SimpleCell(x + 7, y + 6, 1));
            listOfStructure.add(new SimpleCell(x + 11, y + 6, 1));
            listOfStructure.add(new SimpleCell(x + 12, y + 6, 1));
            listOfStructure.add(new SimpleCell(x, y + 7, 1));
            listOfStructure.add(new SimpleCell(x + 1, y + 7, 1));
            listOfStructure.add(new SimpleCell(x + 17, y + 7, 1));
            listOfStructure.add(new SimpleCell(x + 18, y + 7, 1));
            listOfStructure.add(new SimpleCell(x , y + 8, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 8, 1));
            listOfStructure.add(new SimpleCell(x + 7, y + 8, 1));
            listOfStructure.add(new SimpleCell(x + 9, y + 8, 1));
            listOfStructure.add(new SimpleCell(x + 11, y + 8, 1));
            listOfStructure.add(new SimpleCell(x + 15, y + 8, 1));
            listOfStructure.add(new SimpleCell(x + 18, y + 8, 1));
            listOfStructure.add(new SimpleCell(x + 1, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 2, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 5, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 6, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 7, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 8, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 9, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 10, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 11, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 12, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 13, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 15, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 16, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 17, y + 9, 1));
            listOfStructure.add(new SimpleCell(x + 4, y + 10, 1));
            listOfStructure.add(new SimpleCell(x + 9, y + 10, 1));
            listOfStructure.add(new SimpleCell(x + 14, y + 10, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 11, 1));
            listOfStructure.add(new SimpleCell(x + 4, y + 11, 1));
            listOfStructure.add(new SimpleCell(x + 14, y + 11, 1));
            listOfStructure.add(new SimpleCell(x + 15, y + 11, 1));
            listOfStructure.add(new SimpleCell(x + 3, y + 12, 1));
            listOfStructure.add(new SimpleCell(x + 15, y + 12, 1));
            listOfStructure.add(new SimpleCell(x + 5, y + 13, 1));
            listOfStructure.add(new SimpleCell(x + 13, y + 13, 1));
            listOfStructure.add(new SimpleCell(x + 4, y + 14, 1));
            listOfStructure.add(new SimpleCell(x + 5, y + 14, 1));
            listOfStructure.add(new SimpleCell(x + 13, y + 14, 1));
            listOfStructure.add(new SimpleCell(x + 14, y + 14, 1));
        }
    }

    @Override
    public List<Cell> getListOfStructure() {
        return listOfStructure;
    }
}
