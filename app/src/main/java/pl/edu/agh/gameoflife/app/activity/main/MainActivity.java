package pl.edu.agh.gameoflife.app.activity.main;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.gameoflife.R;


@EActivity(R.layout.activity_main)
@Fullscreen
public class MainActivity extends Activity {

/*    @ViewById
    RelativeLayout relative_layout_main;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Remove or change this placeholder text
        /*relative_layout_main.setBackgroundResource(R.drawable.background_animation);
        AnimationDrawable animationDrawable =  (AnimationDrawable) relative_layout_main.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();*/


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
