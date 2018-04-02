package pl.edu.agh.gameoflife.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
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
    private ScaleGestureDetector mScaleDetector;
    private float scaleFactor = 1.0f;

    public AutomatonView(Context context) {
        super(context);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public AutomatonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public AutomatonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
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
        /*mScaleDetector.onTouchEvent(event);
        if(!mScaleDetector.isInProgress() && !params.getIsScaleGestureInProgress()){
            paint(event);
        }
        if(event.getPointerCount() == 1 && event.getAction() == MotionEvent.ACTION_UP) {
            params.setIsScaleGestureInProgress(false);
        }*/

        if(params.getIsZoom()) {
            mScaleDetector.onTouchEvent(event);
        } else {
            paint(event);
        }

        return true;
    }

    protected void paint(MotionEvent event) {
        int x = Math.round(adjustX(event.getX()) / params.getCellSizeInPixels());
        int y = Math.round(adjustY(event.getY()) / params.getCellSizeInPixels());
        EventBus.getInstance().post(new PaintWithBrush(x, y));
        //paintStructure(new GunStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        //paintStructure(new GliderStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        //paintStructure(new PenthadecathlonStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        //paintStructure(new DakotaStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        //paintStructure(new SpaceshipStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        //paintStructure(new FountainStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        //paintStructure(new CrabStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
    }

    protected void paintStructure(Structure structure) {
        for (Cell cell : structure.getListOfStructure()) {
            EventBus.getInstance().post(new PaintWithBrush(cell.getX(), cell.getY()));
        }
    }

    private float adjustX(float x) {
        x -= params.getDrawFocusX();
        x /= params.getScaleFactor();
        x += params.getDrawFocusX();
        return x;
    }

    private float adjustY(float y) {
        y -= params.getDrawFocusY();
        y /= params.getScaleFactor();
        y += params.getDrawFocusY();
        return y;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        float x;
        float y;
        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            x = params.getDrawFocusX() - params.getPreviousFocusX();
            x /= params.getScaleFactor();
            x += params.getPreviousFocusX();
            y = params.getDrawFocusY() - params.getPreviousFocusY();
            y /= params.getScaleFactor();
            y += params.getPreviousFocusY();
            params.setPreviousFocusX(x);
            params.setPreviousFocusY(y);
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            //params.setIsScaleGestureInProgress(true);
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(1.0f, Math.min(scaleFactor, 5.0f));
            params.setScaleFactor(scaleFactor);
            params.setFocusX(detector.getFocusX());
            params.setFocusY(detector.getFocusY());
            return true;
        }
    }
}
