package pl.edu.agh.gameoflife.game.automaton;

public class GameOfLifeAutomatonFactory implements CellularAutomatonFactory {
    @Override
    public CellularAutomaton<?> create(int gridSizeX, int gridSizeY) {
        return new GameOfLifeAutomaton(gridSizeX, gridSizeY);
    }
}
