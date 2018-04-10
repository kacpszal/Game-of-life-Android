package pl.edu.agh.gameoflife.app.view.gameSettings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import pl.edu.agh.gameoflife.R;
import pl.edu.agh.gameoflife.app.util.CellRuleWatcher;
import pl.edu.agh.gameoflife.game.manager.GameManager;
import pl.edu.agh.gameoflife.game.rule.RuleFactory;

@EFragment
public class RulesTab extends Fragment {

    private GameManager gameManager;

    private EditText cellRule;
    private Spinner specialCellRule;
    private Spinner neighborhood;
    private SeekBar neighborhoodRadius;
    private TextView neighborhoodRadiusValue;
    private ToggleButton wrapping;
    private Spinner Structures;

    @ViewById
    Button apply, save, load;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return setViews(inflater, container);
    }

    private View setViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.game_rules_tab, container, false);

        setCellRule(view);
        setSpecialCellRule(view);
        setNeighborhoodRadius(view);
        return view;
    }

    private void setCellRule(View view) {
        cellRule = (EditText) view.findViewById(R.id.cellRule);
        cellRule.addTextChangedListener(new CellRuleWatcher(cellRule));
    }

    private void setNeighborhoodRadius(View view) {
        this.neighborhoodRadius = (SeekBar) view.findViewById(R.id.neighborhoodRadius);
        this.neighborhoodRadiusValue = (TextView) view.findViewById(R.id.valueSeekBar);

        neighborhoodRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                neighborhoodRadiusValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setSpecialCellRule(View view) {
        this.specialCellRule = (Spinner) view.findViewById(R.id.special_cell_rule);
        specialCellRule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    cellRule.setText("");
                    cellRule.setEnabled(true);
                } else {
                    cellRule.setText(RuleFactory.getRuleByName(specialCellRule.getSelectedItem().toString()));
                    cellRule.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    @Click
    void apply(){

    }

    @Click
    void save(){

    }

    @Click
    void load(){

    }

    private void changeRule(String rule) {
        gameManager.getAutomaton().setRule(RuleFactory.createRuleByName(rule));
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}