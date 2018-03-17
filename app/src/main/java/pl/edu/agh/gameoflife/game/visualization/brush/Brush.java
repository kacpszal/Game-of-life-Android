package pl.edu.agh.gameoflife.game.visualization.brush;

import android.graphics.Point;

public interface Brush {
    void paint(Paintable paintable, Point relativeTo);
}
