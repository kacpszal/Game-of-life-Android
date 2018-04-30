

package pl.edu.agh.gameoflife.app.view.gameSettings;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EFragment;

import pl.edu.agh.gameoflife.R;
import pl.edu.agh.gameoflife.game.manager.GameManager;


@EFragment
public class SettingsDialogFragment extends DialogFragment {

    GameManager gameManager;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.game_settings,container,false);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.masterViewPager);
        CustomAdapter adapter = new CustomAdapter(getChildFragmentManager());

        RulesTab rulesTab = new RulesTab();
        rulesTab.setGameManager(gameManager);
        adapter.addFragment("Rules", rulesTab );

        AnimationTab animationTab = new AnimationTab();
        animationTab.setGameManager(gameManager);
        adapter.addFragment("Animation", animationTab);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
}