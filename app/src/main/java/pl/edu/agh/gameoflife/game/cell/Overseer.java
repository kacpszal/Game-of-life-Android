package pl.edu.agh.gameoflife.game.cell;

import pl.edu.agh.gameoflife.game.event.CellStateChange;

public interface Overseer {
    void onCellStateChanged(CellStateChange cellStateChange);
}
