package pl.edu.agh.gameoflife.game.automaton;

import pl.edu.agh.gameoflife.game.manager.GameParams;

public class GameOfLifeAutomatonFactory implements CellularAutomatonFactory {
    private GameParams gameParams;

    public GameOfLifeAutomatonFactory(GameParams gameParams) {
        this.gameParams = gameParams;
    }

    @Override
    public CellularAutomaton<?> create(int gridSizeX, int gridSizeY) {
        return new GameOfLifeAutomaton(gridSizeX, gridSizeY, gameParams);
    }
}
