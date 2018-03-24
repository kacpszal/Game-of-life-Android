package pl.edu.agh.gameoflife.app.activity.main;

import android.graphics.Point;
import android.os.Bundle;

import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import hugo.weaving.DebugLog;
import pl.edu.agh.gameoflife.app.util.DisplayHelper;
import pl.edu.agh.gameoflife.app.view.LoadGridDialogFragment;
import pl.edu.agh.gameoflife.app.view.LoadGridDialogFragment_;
import pl.edu.agh.gameoflife.app.view.SettingsDialogFragment;
import pl.edu.agh.gameoflife.app.view.SettingsDialogFragment_;
import pl.edu.agh.gameoflife.game.automaton.GameOfLifeAutomatonFactory;
import pl.edu.agh.gameoflife.game.automaton.GridCharacteristic;
import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.event.Pause;
import pl.edu.agh.gameoflife.game.event.Reset;
import pl.edu.agh.gameoflife.game.event.Restart;
import pl.edu.agh.gameoflife.game.event.Resume;
import pl.edu.agh.gameoflife.game.event.Save;
import pl.edu.agh.gameoflife.game.manager.GameManager;
import pl.edu.agh.gameoflife.game.manager.GameParams;
import pl.edu.agh.gameoflife.game.rule.NeighborCountBasedRule;
import pl.edu.agh.gameoflife.game.visualization.cell.SimpleCellColors;
import pl.edu.agh.gameoflife.util.EventBus;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

@EBean
public class MainPresenter {
    GameManager gameManager;

    @RootContext
    MainActivity activity;

    @Bean
    DisplayHelper displayHelper;

    void onActivityCreate() {
        saveOrientation();
    }


    private void saveOrientation() {
        activity.lastOrientation = displayHelper.getScreenOrientation();
    }

    void onActivityResume() {
        EventBus.getInstance().register(this);
    }

    void onActivityPause() {
        EventBus.getInstance().unregister(this);
    }

    public Bundle saveGameState() {
        return gameManager.saveGameState();
    }

    void startGame() {
        gameManager = new GameManager(
                activity.gameState,
                activity.automatonView,
                new GameOfLifeAutomatonFactory(),
                getGameParams()
        );

        gameManager.createGame();

        if (activity.paused) {
            onPause();
        }
    }

    private GameParams getGameParams() {
        Point displaySize = displayHelper.getDisplaySize();
        int cellSize = getOptimalCellSize(displaySize, displayHelper.isLandscape());

        return new GameParams.Builder(displaySize, cellSize)
                .withScreenOrientation(displayHelper.getScreenOrientation())
                .withGameCharacteristic(new GridCharacteristic(0.00f, Cell.STATE_ALIVE))
                .withCellColors(new SimpleCellColors())
                .withFps(15)
                .withStartPaused(activity.paused)
                .build();
    }

    private int getOptimalCellSize(Point displaySize, boolean isLandscape) {
        int cellSize = 1;
        int limitX = 220;
        int limitY = 300;

        if (isLandscape) {
            int temp = limitX;
            limitX = limitY;
            limitY = temp;
        }

        while (displaySize.x / cellSize > limitX || displaySize.y / cellSize > limitY) {
            cellSize++;
        }

        return cellSize;
    }

    void onPause() {
        EventBus.getInstance().post(new Pause());
        setUiPausedState(true);
    }

    void onResume() {
        EventBus.getInstance().post(new Resume());
        setUiPausedState(false);
    }

    private void setUiPausedState(boolean paused) {
        activity.resume.setVisibility(paused ? VISIBLE : GONE);
        activity.pause.setVisibility(paused ? GONE : VISIBLE);
        activity.paused = paused;
    }

    void onResetGame() {
        onPause();
        EventBus.getInstance().post(new Reset());
    }

    @DebugLog
    @Subscribe
    public void onRulesChanged(NeighborCountBasedRule rule) {
        gameManager.getAutomaton().setRule(rule);
    }

    void onRestartGame() {
        EventBus.getInstance().post(new Restart());
    }

    void onSaveGame() {
        EventBus.getInstance().post(new Save());
    }

    void onLoadGame() {
        LoadGridDialogFragment dialogFragment = LoadGridDialogFragment_.builder().build();
        dialogFragment.setGameManager(gameManager);
        dialogFragment.show(activity.getFragmentManager(), "loadGrid");
    }

    void onSettings() {
        SettingsDialogFragment dialogFragment = SettingsDialogFragment_.builder().build();
        dialogFragment.setGameManager(gameManager);
        dialogFragment.show(activity.getFragmentManager(), "settings");
    }
}
