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

import org.androidannotations.annotations.EFragment;

import pl.edu.agh.gameoflife.R;
import pl.edu.agh.gameoflife.app.util.CellRuleWatcher;
import pl.edu.agh.gameoflife.game.manager.GameManager;
import pl.edu.agh.gameoflife.game.rule.RuleFactory;
import pl.edu.agh.gameoflife.game.structures.StructureFactory;

@EFragment
public class RulesTab extends Fragment {

    private GameManager gameManager;

    private EditText cellRule;
    private Spinner specialCellRule;
    private Spinner neighborhood;
    private SeekBar neighborhoodRadius;
    private TextView neighborhoodRadiusValue;
    private ToggleButton wrapping;
    private Spinner structures;
    private Button apply;
    private Button save;
    private Button load;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return setViews(inflater, container);
    }

    private View setViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.game_rules_tab, container, false);
        this.neighborhood = (Spinner) view.findViewById(R.id.neighborhood);

        setCellRule(view);
        setSpecialCellRule(view);
        setNeighborhoodRadius(view);
        setButtons(view);
        setWrapping(view);
        setStructure(view);
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
                setStructureInGameParams();
                setWrappingInGameParams();
                setCellRuleInGameParams();
                setNeighborhoodInGameParams();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void setCellRule(View view) {
        cellRule = (EditText) view.findViewById(R.id.cellRule);
        cellRule.addTextChangedListener(new CellRuleWatcher(cellRule));
    }

    private void setNeighborhoodRadius(View view) {
        neighborhoodRadius = (SeekBar) view.findViewById(R.id.neighborhoodRadius);
        neighborhoodRadiusValue = (TextView) view.findViewById(R.id.valueSeekBar);

        neighborhoodRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                neighborhoodRadiusValue.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void setSpecialCellRule(View view) {
        specialCellRule = (Spinner) view.findViewById(R.id.special_cell_rule);
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
        specialCellRule.setSelection(RuleFactory.getPositionByName(gameManager.getParams().getCellRule()));
    }

    private void setWrapping(View view) {
        wrapping = (ToggleButton) view.findViewById(R.id.wrapping);
    }

    private void setWrappingInGameParams() {
        gameManager.getParams().setMapWrapping(wrapping.isChecked());
    }

    private void setStructure(View view) {
        structures = (Spinner) view.findViewById(R.id.structures);
        structures.setSelection(StructureFactory.getPositionByName(gameManager.getParams().getStructure()));
    }

    private void setCellRuleInGameParams() {
        if (cellRule.isEnabled() == true) {

        } else {
            gameManager.getParams().setCellRule(specialCellRule.getSelectedItem().toString());
            gameManager.getAutomaton().changeRule();
        }
    }

    private void setStructureInGameParams() {
        gameManager.getParams().setStructure(structures.getSelectedItem().toString());
    }

    private void setNeighborhoodInGameParams() {
        String neighbor = neighborhood.getSelectedItem().toString();
        gameManager.getParams().setCellNeighboorhood(neighbor);
        gameManager.getAutomaton().getGridHandler().getCurrent().changeCellNeighborhood(neighbor);
    }

    public void setGameManager(GameManager gameManager) {
        gameManager = gameManager;
    }
}