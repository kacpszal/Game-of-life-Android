package pl.edu.agh.gameoflife.app.activity.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import pl.edu.agh.gameoflife.R;


@EActivity(R.layout.activity_main)
@Fullscreen
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Remove or change this placeholder text
        /*TransitionDrawable transition =  (TransitionDrawable) activity_main.getBackground();
        transition.isCrossFadeEnabled();
        transition.startTransition(4500);*/


    }

    @Click
    public void play (View view){
        GameOfLifeActivity_.intent(this).start();
    }

    @Click
    public void exit(View view){
        finish();
        System.exit(0);
    }
}
