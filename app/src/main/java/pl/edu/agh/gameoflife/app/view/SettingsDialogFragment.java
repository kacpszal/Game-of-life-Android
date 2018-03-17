package pl.edu.agh.gameoflife.app.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import org.androidannotations.annotations.EFragment;

import pl.edu.agh.gameoflife.R;
import pl.edu.agh.gameoflife.game.automaton.GridCharacteristic;
import pl.edu.agh.gameoflife.game.manager.GameManager;

/**
 * Created by grzegorz on 11/19/17.
 */

@EFragment
public class SettingsDialogFragment extends DialogFragment implements View.OnClickListener {

    GameManager gameManager;

    Button cofnij, zatwierdz;
    SeekBar zapelnienie, wielkoscProstokata;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, null);
        initDialog(view);
        return view;
    }

    private void initDialog(View view) {
        this.initButtons(view);
        this.initSeekBars(view);
        setCancelable(false);
    }

    private void initButtons(View view) {
        cofnij = (Button) view.findViewById(R.id.cofnij);
        zatwierdz = (Button) view.findViewById(R.id.zatwierdz);
        cofnij.setOnClickListener(this);
        zatwierdz.setOnClickListener(this);
    }

    private void initSeekBars(View view) {
        zapelnienie = (SeekBar) view.findViewById(R.id.zapelnienie);
        wielkoscProstokata = (SeekBar) view.findViewById(R.id.wielkosciProstokata);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cofnij) {
            dismiss();
        } else if (view.getId() == R.id.zatwierdz) {
            GridCharacteristic gridCharacteristic = gameManager.getParams().getGridCharacteristic();
            gridCharacteristic.setCellPercentage(zapelnienie.getProgress() / 100.0f);
            gridCharacteristic.setRectangleScale(wielkoscProstokata.getProgress() / 100.0f);
            dismiss();
        }
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}
