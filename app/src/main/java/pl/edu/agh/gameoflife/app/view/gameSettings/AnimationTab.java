package pl.edu.agh.gameoflife.app.view.gameSettings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.androidannotations.annotations.EFragment;

import pl.edu.agh.gameoflife.R;
import pl.edu.agh.gameoflife.game.manager.GameManager;
import pl.edu.agh.gameoflife.game.manager.GameParams;

@EFragment
public class AnimationTab extends Fragment {

    private GameManager gameManager;

    private SeekBar speedAnimation;
    private TextView speedAnimationValue;
    private SeekBar stepAnimation;
    private TextView stepAnimationValue;
    private Button apply;
    private Button save;
    private Button load;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.animation_tab, container, false);

        setSpeedAnimation(view);
        setStepAnimation(view);
        setButtons(view);
        return view;
    }

    private void setButtons(View view) {
        apply = (Button) view.findViewById(R.id.apply);
        save = (Button) view.findViewById(R.id.save);
        load = (Button) view.findViewById(R.id.load);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSpeedAnimationInGameParams();
                setStepAnimationInGameParams();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
    }

    private void setSpeedAnimation(View view) {
        speedAnimation = (SeekBar) view.findViewById(R.id.animationSpeed);
        speedAnimationValue = (TextView) view.findViewById(R.id.valueAnimationSpeed);

        speedAnimation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speedAnimationValue.setText(String.valueOf(progress + 1));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        speedAnimationValue.setText(String.valueOf(gameManager.getParams().getSpeedAnimation()));
        speedAnimation.setProgress(gameManager.getParams().getSpeedAnimation() - 1);
    }

    private void setStepAnimation(View view){
        stepAnimation = (SeekBar) view.findViewById(R.id.animationStep);
        stepAnimationValue = (TextView) view.findViewById(R.id.valueAnimationStep);

        stepAnimation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                stepAnimationValue.setText(String.valueOf(progress + 1));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        stepAnimationValue.setText(String.valueOf(gameManager.getParams().getStepAnimation()));
        stepAnimation.setProgress(gameManager.getParams().getStepAnimation() - 1);
    }

    private void setSpeedAnimationInGameParams() {
        gameManager.getParams().setSpeedAnimation(Integer.parseInt(speedAnimationValue.getText().toString()));
    }

    private void setStepAnimationInGameParams() {
        gameManager.getParams().setStepAnimation(Integer.parseInt(stepAnimationValue.getText().toString()));
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
