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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

}
