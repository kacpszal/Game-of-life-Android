package pl.edu.agh.gameoflife.game.automaton;

import android.os.Parcelable;

import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.grid.Grid;
import pl.edu.agh.gameoflife.game.rule.Rule;
import pl.edu.agh.gameoflife.game.visualization.brush.Paintable;

public interface CellularAutomaton<T extends Cell> extends Parcelable, Paintable {
    int getSizeX();
    int getSizeY();
    int getDefaultCellState();
    void reset();

    void randomFill(GridCharacteristic gridCharacteristic);

    void fillFromGrid(Grid grid);
    void step();
    void step(int count);
    Grid<T> getCurrentState();
    void setState(Grid<T> grid);
    Rule<T> getRule();
    void setRule(Rule<T> rule);
}
