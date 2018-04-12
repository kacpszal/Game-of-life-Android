package pl.edu.agh.gameoflife.game.automaton;

import pl.edu.agh.gameoflife.game.manager.GameParams;

public class GameOfLifeAutomatonFactory implements CellularAutomatonFactory {

    public GameOfLifeAutomatonFactory() {
    }

    @Override
    public CellularAutomaton<?> create(GameParams gameParams) {
        return new GameOfLifeAutomaton(gameParams);
    }
}
