package pl.edu.agh.gameoflife.game.visualization.brush;

import android.graphics.Point;

import pl.edu.agh.gameoflife.game.cell.Cell;

public class DefaultBlockBrush implements Brush {

    private Paintable paintable;
    private Point startingPoint;
    private int state;

    @Override
    public final void paint(Paintable paintable, Point relativeTo) {
        setPaintable(paintable);
        setStartingPoint(relativeTo);
        doPaint();
    }

    private void setPaintable(Paintable paintable) {
        this.paintable = paintable;
    }

    private void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    private void paintWith(int state) {
        this.state = state;
    }

    private void paint(int relX, int relY) {
        paint(relX, relY, state);
    }

    private void paint(int relX, int relY, int state) {
        paintable.paint(startingPoint.x + relX, startingPoint.y + relY, state);
    }

    private void doPaint() {
        paintWith(Cell.STATE_ALIVE);
        paint(0, 0);
        //paint(0, 1);
        //paint(1, 0);
        //paint(1, 1);
    }
}
