package pl.edu.agh.gameoflife.game.automaton;

import pl.edu.agh.gameoflife.game.manager.GameParams;

public interface CellularAutomatonFactory {
    CellularAutomaton<?> create(GameParams params);
}
