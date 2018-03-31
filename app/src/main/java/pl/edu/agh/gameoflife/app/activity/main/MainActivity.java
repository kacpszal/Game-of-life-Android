package pl.edu.agh.gameoflife.app.activity.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.couchbase.lite.CouchbaseLiteException;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

import hugo.weaving.DebugLog;
import pl.edu.agh.gameoflife.R;
import pl.edu.agh.gameoflife.app.view.AutomatonView;
import pl.edu.agh.gameoflife.persistence.DataManager;

@EActivity(R.layout.activity_main)
@Fullscreen
public class MainActivity extends Activity {
    @InstanceState
    boolean paused;

    @InstanceState
    int lastOrientation;

    @InstanceState
    Bundle gameState;

    @Bean
    MainPresenter presenter;

    @ViewById
    AutomatonView automatonView;

    @ViewById
    ImageButton zoom, draw, reset, restart, save, load, settings, pause, resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onActivityCreate();

//        removeDataOnLoad();
    }

    private void removeDataOnLoad() {
        DataManager manager = DataManager.getSharedInstance(getApplicationContext());

        try {
            manager.database.delete();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        DataManager.instance = null;
    }

    @Override
    @DebugLog
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        gameState = presenter.saveGameState();
    }

    @AfterViews
    void afterViews() {
        presenter.startGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onActivityResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onActivityPause();
    }

    @Click
    void draw() {
        presenter.onDraw();
    }

    @Click
    void zoom() {
        presenter.onZoom();
    }

    @Click
    void pause() {
        presenter.onPause();
    }

    @Click
    void resume() {
        presenter.onResume();
    }

    @Click
    void reset() {
        presenter.onResetGame();
    }

    @Click
    void restart() {
        presenter.onRestartGame();
    }

    @Click
    void save() {
        presenter.onSaveGame();
    }

    @Click
    void load() {
        presenter.onLoadGame();
    }

    @Click
    void settings() {
        presenter.onSettings();
    }
}
