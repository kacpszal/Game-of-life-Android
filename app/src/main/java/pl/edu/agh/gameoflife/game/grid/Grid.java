package pl.edu.agh.gameoflife.game.grid;

import android.os.Parcelable;

import pl.edu.agh.gameoflife.game.cell.Cell;

public interface Grid<T extends Cell> extends Parcelable {
    int getSizeX();
    int getSizeY();
    T getCell(int x, int y);
    void putCell(T cell);
}
