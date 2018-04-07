package pl.edu.agh.gameoflife.game.automaton;

import android.os.Parcel;

import pl.edu.agh.gameoflife.game.cell.CellFactory;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;
import pl.edu.agh.gameoflife.game.cell.SimpleCellFactory;
import pl.edu.agh.gameoflife.game.rule.ConwaysRule;
import pl.edu.agh.gameoflife.game.rule.Rule;

public class GameOfLifeAutomaton extends AbstractCellularAutomaton<SimpleCell> {
    public GameOfLifeAutomaton(int gridSizeX, int gridSizeY) {
        super(gridSizeX, gridSizeY);
    }

    public GameOfLifeAutomaton(Parcel source) {
        super(source);
    }

    @Override
    protected CellFactory<SimpleCell> getFactory() {
        return new SimpleCellFactory();
    }

    @Override
    public Rule<SimpleCell> createRule() {
        return new ConwaysRule();
        // TODO: Implement rule change
        //return new MazeRule();
        //return new GrowthRule();
        //return new FlakesRule();
        //return new CoralRule();
        //return new CitiesSurroundedByWallRule();
        //return new ReplicatorRule();
        //return new SeedsRule();
        //return new ServietteRule();
        //return new ThreeFourRule();
        //return new AssimilationRule();
    }

    public static final Creator<GameOfLifeAutomaton> CREATOR = new Creator<GameOfLifeAutomaton>() {
        public GameOfLifeAutomaton createFromParcel(Parcel source) {
            return new GameOfLifeAutomaton(source);
        }

        public GameOfLifeAutomaton[] newArray(int size) {
            return new GameOfLifeAutomaton[size];
        }
    };
}
