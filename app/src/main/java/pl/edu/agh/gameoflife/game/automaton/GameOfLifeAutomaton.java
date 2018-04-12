package pl.edu.agh.gameoflife.game.automaton;

import android.os.Parcel;

import pl.edu.agh.gameoflife.game.cell.CellFactory;
import pl.edu.agh.gameoflife.game.cell.SimpleCell;
import pl.edu.agh.gameoflife.game.cell.SimpleCellFactory;
import pl.edu.agh.gameoflife.game.manager.GameParams;
import pl.edu.agh.gameoflife.game.rule.Rule;
import pl.edu.agh.gameoflife.game.rule.RuleFactory;

public class GameOfLifeAutomaton extends AbstractCellularAutomaton<SimpleCell> {

    public GameOfLifeAutomaton(int gridSizeX, int gridSizeY, GameParams gameParams) {
        super(gridSizeX, gridSizeY, gameParams);
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
        return RuleFactory.createRuleByName(gameParams.getCellRule());
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
