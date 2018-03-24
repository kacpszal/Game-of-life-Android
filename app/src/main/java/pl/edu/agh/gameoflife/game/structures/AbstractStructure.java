package pl.edu.agh.gameoflife.game.structures;

import java.util.List;

import pl.edu.agh.gameoflife.game.cell.Cell;

public abstract class AbstractStructure implements Structure {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected List<Cell> listOfStructure;

    @Override
    abstract public List<Cell> getListOfStructure();

    protected AbstractStructure(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
