package pl.edu.agh.gameoflife.game.grid;

import android.os.Parcel;

import java.util.HashSet;
import java.util.Set;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.cell.CellFactory;
import pl.edu.agh.gameoflife.game.cell.Overseer;
import pl.edu.agh.gameoflife.game.event.CellStateChange;
import pl.edu.agh.gameoflife.game.manager.GameParams;
import pl.edu.agh.gameoflife.game.neighborhood.CellNeighborhood;
import pl.edu.agh.gameoflife.game.neighborhood.MooreNeighborhood;
import pl.edu.agh.gameoflife.game.neighborhood.VonNeumannNeighborhood;
import pl.edu.agh.gameoflife.util.EventBus;

public class NormalGrid<T extends Cell> implements Grid<T>, Overseer {

    public static final Creator<NormalGrid> CREATOR = new Creator<NormalGrid>() {
        public NormalGrid createFromParcel(Parcel source) {
            return new NormalGrid(source);
        }

        public NormalGrid[] newArray(int size) {
            return new NormalGrid[size];
        }
    };

    protected final int sizeX;
    protected final int sizeY;
    protected final T[][] cells;
    protected final Set<Long> cellIds;
    protected GameParams gameParams;
    protected CellNeighborhood cellNeighborhood;
    private final CellFactory<T> cellFactory;


    public NormalGrid(int sizeX, int sizeY, GameParams gameParams, CellFactory<T> cellFactory) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.cellFactory = cellFactory;
        cells = (T[][]) new Cell[sizeY][sizeX];
        cellIds = new HashSet<>(sizeY * sizeX);
        this.gameParams = gameParams;
        cellNeighborhood = createNeighboorhood(gameParams.getCellNeighboorhood());
        createCells(sizeX, sizeY, cellFactory);
    }

    public NormalGrid(Grid<T> other, CellFactory<T> cellFactory, GameParams gameParams) {
        this(other.getSizeX(), other.getSizeY(), gameParams, cellFactory);
        copyCells(other);
    }

    protected NormalGrid(Parcel in) {
        this.cellFactory = (CellFactory<T>) in.readSerializable();
        this.sizeX = in.readInt();
        this.sizeY = in.readInt();
        int flattenedLength = in.readInt();

        cells = (T[][]) new Cell[sizeY][sizeX];
        cellIds = new HashSet<>(sizeY * sizeX);

        for (int j = 0; j < getSizeY(); j++) {
            for (int i = 0; i < getSizeX(); i++) {
                final int[] flattened = new int[flattenedLength];
                in.readIntArray(flattened);
                final T cell = cellFactory.inflate(flattened);
                putCell(cell);
            }
        }
    }

    protected void createCells(int sizeX, int sizeY, CellFactory<T> cellFactory) {
        for (int j = 0; j < sizeY; j++) {
            for (int i = 0; i < sizeX; i++) {
                T cell = cellFactory.create(i, j);
                putCell(cell);
            }
        }
    }

    protected void copyCells(Grid<T> other) {
        for (int j = 0; j < sizeY; j++) {
            for (int i = 0; i < sizeX; i++) {
                putCell(other.getCell(i, j));
            }
        }
    }

    @Override
    public void putCell(T cell) {
        int y;
        int x;
        if(gameParams.getMapWrapping()) {
            y = normalizeY(cell.getY());
            x = normalizeX(cell.getX());
        } else {
            y = cell.getY();
            x = cell.getX();
        }
        maintainIds(cell, x, y);
        cell.setOverseer(this);

        if (cells[y][x] != null) {
            cells[y][x].setOverseer(null);
        }

        cells[y][x] = cell;
    }

    private void maintainIds(T cell, int x, int y) {
        if (cells[y][x] != null) {
            cellIds.remove(cells[y][x].getId());
        }

        cellIds.add(cell.getId());
    }

    @Override
    public void onCellStateChanged(CellStateChange cellStateChange) {
        EventBus.getInstance().post(cellStateChange);

        if (cellIds.contains(cellStateChange.cell.getId())) {
            cellNeighborhood.notifyNeighbors(cellStateChange.cell);
        }
    }

    @Override
    public int getSizeX() {
        return sizeX;
    }

    @Override
    public int getSizeY() {
        return sizeY;
    }

    @Override
    public T getCell(int x, int y) {
        if(gameParams.getMapWrapping()) {
            return cells[normalizeY(y)][normalizeX(x)];
        }

        if(x < 0 || x >= sizeX || y < 0 || y >= sizeY) {
            return null;
        }

        return cells[y][x];
    }

    public int normalizeY(int y) {
        while (y < 0) {
            y += sizeY;
        }

        while (y >= sizeY) {
            y -= sizeY;
        }

        return y;
    }

    public int normalizeX(int x) {
        while (x < 0) {
            x += sizeX;
        }

        while (x >= sizeX) {
            x -= sizeX;
        }

        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NormalGrid that = (NormalGrid) o;

        if (sizeX != that.sizeX) return false;
        if (sizeY != that.sizeY) return false;

        for (int j = 0; j < getSizeY(); j++) {
            for (int i = 0; i < getSizeX(); i++) {
                Cell cell = getCell(i, j);
                Cell otherCell = that.getCell(i, j);

                if (!otherCell.equals(cell)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = sizeX;
        result = 31 * result + sizeY;

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(cellFactory);
        dest.writeInt(this.sizeX);
        dest.writeInt(this.sizeY);

        final T dummy = cells[0][0];
        dest.writeInt(cellFactory.flatten(dummy).length);

        for (int j = 0; j < getSizeY(); j++) {
            for (int i = 0; i < getSizeX(); i++) {
                dest.writeIntArray(cellFactory.flatten(cells[j][i]));
            }
        }
    }

    private CellNeighborhood createNeighboorhood(String cellNeighborhood) {
        if( cellNeighborhood.equals("Moore")){
            return new MooreNeighborhood(this);
        }
        else{
            return new VonNeumannNeighborhood(this);
        }
    }

    @Override
    public void changeCellNeighborhood(String neighborhood) {
        this.cellNeighborhood = createNeighboorhood(neighborhood);
    }
}
