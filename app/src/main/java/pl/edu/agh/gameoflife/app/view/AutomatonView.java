package pl.edu.agh.gameoflife.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import hugo.weaving.DebugLog;
import pl.edu.agh.gameoflife.game.automaton.CellularAutomaton;
import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.event.PaintWithBrush;
import pl.edu.agh.gameoflife.game.manager.GameParams;
import pl.edu.agh.gameoflife.game.structures.Structure;
import pl.edu.agh.gameoflife.util.EventBus;

public class AutomatonView extends SurfaceView implements SurfaceHolder.Callback {
    private CellularAutomaton automaton;
    private GameParams params;
    private AutomatonThread thread;

    public AutomatonView(Context context) {
        super(context);
    }

    public AutomatonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutomatonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @DebugLog
    public void init(CellularAutomaton automaton, GameParams params) {
        this.automaton = automaton;
        this.params = params;

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    @DebugLog
    public void surfaceCreated(SurfaceHolder holder) {
        if (thread == null || thread.getState() == Thread.State.TERMINATED) {
            thread = new AutomatonThread(automaton, holder, params, getContext());
            thread.start();
        }

        thread.setRunning(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //
    }

    @Override
    @DebugLog
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.setRunning(false);
        waitForThreadToDie();
    }

    @DebugLog
    private void waitForThreadToDie() {
        while (true) {
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        paint(event);

        return true;
    }

    protected void paint(MotionEvent event) {
        int x = Math.round(event.getX() / params.getCellSizeInPixels());
        int y = Math.round(event.getY() / params.getCellSizeInPixels());
        EventBus.getInstance().post(new PaintWithBrush(x, y));
        //paintStructure(new GunStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        //paintStructure(new GliderStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        //paintStructure(new PenthadecathlonStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
    }

    protected void paintStructure(Structure structure) {
        for (Cell cell : structure.getListOfStructure()) {
            EventBus.getInstance().post(new PaintWithBrush(cell.getX(), cell.getY()));
        }
    }
}
