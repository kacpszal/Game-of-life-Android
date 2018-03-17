package pl.edu.agh.gameoflife.game.visualization.cell;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.gameoflife.game.cell.Cell;

public class SimpleCellColors implements CellColors {

    private final Map<Integer, Paint> paintMap;

    public SimpleCellColors() {
        paintMap = new HashMap<>(2);
        paintMap.put(Cell.STATE_DEAD, createPaint("#ffffff"));
        paintMap.put(Cell.STATE_ALIVE, createPaint("#000000"));
    }

    private Paint createPaint(String color) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor(color));

        return paint;
    }

    @Override
    public Paint getPaint(int state) {
        return paintMap.get(state);
    }

}
