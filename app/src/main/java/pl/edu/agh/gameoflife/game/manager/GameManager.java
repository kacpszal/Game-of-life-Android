package pl.edu.agh.gameoflife.game.manager;

import android.os.Bundle;

import pl.edu.agh.gameoflife.app.view.AutomatonView;
import pl.edu.agh.gameoflife.game.automaton.CellularAutomaton;
import pl.edu.agh.gameoflife.game.automaton.CellularAutomatonFactory;

public class GameManager {
    public static final String KEY_AUTOMATON = "automaton";
    private final AutomatonView automatonView;
    private CellularAutomaton automaton;
    private GameParams params;

    public GameManager(Bundle savedGameState, AutomatonView automatonView, CellularAutomatonFactory factory, GameParams params) {
        this.automatonView = automatonView;
        this.params = params;

        if (savedGameState != null) {
            restoreGameState(savedGameState);

        } else {
            this.automaton = createAutomaton(factory, params);
        }
    }

    private CellularAutomaton createAutomaton(CellularAutomatonFactory factory, GameParams params) {
        CellularAutomaton<?> automaton = factory.create(
                params.getGridSizeX(),
                params.getGridSizeY()
        );

        automaton.randomFill(params.getGridCharacteristic());

        return automaton;
    }

    public CellularAutomaton getAutomaton() {
        return automaton;
    }

    public void createGame() {
        initView(
                automatonView,
                automaton,
                params
        );
    }

    private static void initView(AutomatonView automatonView, CellularAutomaton automaton, GameParams params) {
        automatonView.init(
                automaton,
                params
        );
    }

    public Bundle saveGameState() {
        Bundle gameState = new Bundle();
        gameState.putParcelable(KEY_AUTOMATON, getAutomaton());

        return gameState;
    }

    public void restoreGameState(Bundle gameState) {
        automaton = gameState.getParcelable(KEY_AUTOMATON);
    }

    public GameParams getParams() {
        return params;
    }
}
