package pl.edu.agh.gameoflife.app.view;

import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;

import pl.edu.agh.gameoflife.R;
import pl.edu.agh.gameoflife.game.event.Save;
import pl.edu.agh.gameoflife.game.manager.GameManager;
import pl.edu.agh.gameoflife.persistence.GridDao;
import pl.edu.agh.gameoflife.persistence.GridDaoRepository;
import pl.edu.agh.gameoflife.persistence.GridDaoToGrid;
import pl.edu.agh.gameoflife.util.EventBus;

@EFragment
public class LoadGridDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener {

    GameManager gameManager;
    ListView gridListView;
    GridDao focusedGrid;
    EditText saveName;

    List<GridDao> gridsLoadedFromDatabase = new ArrayList<>();

    Button deleteGridButton;
    Button loadGridButton;
    Button saveGridButton;

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return setViews(inflater);
    }

    private View setViews(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.load_grid_dialog, null, false);
        gridListView = (ListView) view.findViewById(R.id.listOfGrids);

        saveName = (EditText) view.findViewById(R.id.saveName);
        deleteGridButton = (Button) view.findViewById(R.id.delete_button);
        loadGridButton = (Button) view.findViewById(R.id.load_button);
        saveGridButton = (Button) view.findViewById(R.id.save_button);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        updateGridListView();
        deleteGridButton.setOnClickListener(getDeleteListener());
        loadGridButton.setOnClickListener(getLoadGridListener());
        saveGridButton.setOnClickListener(getSaveGridListener());
    }

    private void updateGridListView() {
        gridsLoadedFromDatabase = GridDaoRepository.getGrids(getActivity().getApplicationContext());

        List<String> gridCreationText = new ArrayList<>();
        for (GridDao grid : gridsLoadedFromDatabase) {
            gridCreationText.add(grid.getSaveText());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.grid_item, R.id.grid_id, gridCreationText);

        gridListView.setAdapter(adapter);
        gridListView.setOnItemClickListener(this);
        saveName.setText("Game: "+ gridListView.getAdapter().getCount());
    }

    private View.OnClickListener getDeleteListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (focusedGrid != null) {
                    GridDaoRepository.delete(focusedGrid, getActivity().getApplicationContext());
                    updateGridListView();
                }
            }
        };
    }

    private View.OnClickListener getLoadGridListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (focusedGrid != null) {
                    gameManager.getAutomaton().fillFromGrid(GridDaoToGrid.parse(focusedGrid, gameManager));
//                    dismiss();
                }
            }
        };
    }

    private View.OnClickListener getSaveGridListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = saveName.getText().toString();
                Boolean nameExists = false;
                for (int k = 0; k < gridListView.getChildCount(); k++) {
                    if (name.equals(gridsLoadedFromDatabase.get(k).getSaveText() )) { nameExists = true; }
                }

                if(nameExists){
                    Toast.makeText(getActivity().getApplicationContext(), "Name already exists!", Toast.LENGTH_LONG).show();
                } else {
                    EventBus.getInstance().post(new Save(name));
                    updateGridListView();
                }
            }
        };
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        focusedGrid = gridsLoadedFromDatabase.get(i);
        saveName.setText(focusedGrid.getSaveText());
        for (int k = 0; k < gridListView.getChildCount(); k++) {
            if (i == k) {
                gridListView.getChildAt(k).setBackgroundColor(Color.GRAY);
            } else {
                gridListView.getChildAt(k).setBackgroundColor(Color.WHITE);
            }
        }
    }
}
