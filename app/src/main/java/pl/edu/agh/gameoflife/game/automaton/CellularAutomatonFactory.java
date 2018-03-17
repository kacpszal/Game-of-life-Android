package pl.edu.agh.gameoflife.game.automaton;

public interface CellularAutomatonFactory {
    CellularAutomaton<?> create(int gridSizeX, int gridSizeY);
}
