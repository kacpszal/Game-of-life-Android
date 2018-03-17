package pl.edu.agh.gameoflife.game.automaton;

import android.os.Parcel;

import java.util.Random;

import hugo.weaving.DebugLog;
import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.CellFactory;
import pl.edu.agh.gameoflife.game.grid.EndlessGridHandler;
import pl.edu.agh.gameoflife.game.grid.Grid;
import pl.edu.agh.gameoflife.game.grid.GridHandler;
import pl.edu.agh.gameoflife.game.rule.Rule;
import pl.edu.agh.gameoflife.game.transformer.GridTransformer;
import pl.edu.agh.gameoflife.game.transformer.ThreadedGridTransformer;

import static java.lang.Math.abs;

abstract class AbstractCellularAutomaton<T extends Cell> implements CellularAutomaton<T> {
    final GridTransformer<T> gridTransformer;
    protected int gridSizeX;
    protected int gridSizeY;
    GridHandler<T> gridHandler;
    Rule<T> rule;

    public AbstractCellularAutomaton(int gridSizeX, int gridSizeY) {
        this.gridSizeX = gridSizeX;
        this.gridSizeY = gridSizeY;
        this.gridHandler = getGridHandler();
        this.gridTransformer = getGridTransformer();
        this.rule = createRule();
    }

    protected AbstractCellularAutomaton(Parcel in) {
        gridHandler = getGridHandler();
        gridTransformer = getGridTransformer();

        this.gridSizeX = in.readInt();
        this.gridSizeY = in.readInt();
        this.rule = (Rule<T>) in.readSerializable();
        final Class<Grid<T>> gridClass = (Class<Grid<T>>) in.readSerializable();
        final Grid<T> grid = in.readParcelable(gridClass.getClassLoader());

        gridHandler.setCurrent(grid);
    }

    protected abstract Rule<T> createRule();

    protected EndlessGridHandler<T> getGridHandler() {
        return new EndlessGridHandler<T>(gridSizeX, gridSizeY, getFactory());
    }

    protected GridTransformer<T> getGridTransformer() {
        return new ThreadedGridTransformer<T>();
    }

    abstract protected CellFactory<T> getFactory();

    @Override
    public Rule<T> getRule() {
        return rule;
    }

    @Override
    public void setRule(Rule<T> rule) {
        this.rule = rule;
    }

    @Override
    public final int getSizeX() {
        return gridSizeX;
    }

    @Override
    public final int getSizeY() {
        return gridSizeY;
    }

    @Override
    public void reset() {
        Grid<T> grid = getCurrentState();
        T newBornCell = getFactory().create(0, 0);

        for (int j = 0; j < gridSizeY; j++) {
            for (int i = 0; i < gridSizeX; i++) {
                grid.getCell(i, j).reset(newBornCell.getState());
            }
        }
    }

    @Override
    public int getDefaultCellState() {
        return getFactory().create(0, 0).getState();
    }

    @Override
    public void randomFill(GridCharacteristic gridCharacteristic) {
        Grid<T> grid = getCurrentState();
        Random random = new Random();

        Float scale = gridCharacteristic.getRectangleScale();
        int scaledSizeY = (int) (gridSizeY * scale / 2);
        int scaledSizeX = (int) (gridSizeX * scale / 2);
        int centreY = gridSizeY / 2;
        int centreX = gridSizeX / 2;

        for (int j = 0; j < gridSizeY; j++) {
            for (int i = 0; i < gridSizeX; i++) {
                if ((abs(centreY - j) <= scaledSizeY) && (abs(centreX - i) <= scaledSizeX)) {
                    if (random.nextFloat() < gridCharacteristic.getCellPercentage()) {
                        grid.getCell(i, j).setState(gridCharacteristic.getCellState());
                    }
                }
            }
        }
    }

    @Override
    public void fillFromGrid(Grid g) {
        Grid<T> grid = getCurrentState();

        for (int j = 0; j < gridSizeY; j++) {
            for (int i = 0; i < gridSizeX; i++) {
                grid.getCell(i, j).setState(g.getCell(i, j).getState());
            }
        }
    }

    @Override
    public void step(int count) {
        for (int i = 0; i < count; i++) {
            step();
        }
    }

    @Override
    public void step() {
        gridTransformer.transform(gridHandler.getCurrent(), rule);
    }

    @Override
    public final Grid<T> getCurrentState() {
        return gridHandler.getCurrent();
    }

    @Override
    @DebugLog
    public void setState(Grid<T> grid) {
        gridHandler.setCurrent(grid);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.gridSizeX);
        dest.writeInt(this.gridSizeY);
        dest.writeSerializable(this.rule);
        final Grid<T> currentState = getCurrentState();
        dest.writeSerializable(currentState.getClass());
        dest.writeParcelable(currentState, flags);
    }

    @Override
    public void paint(int x, int y, int state) {
        getCurrentState().getCell(x, y).setState(state);
    }
}
