package pl.edu.agh.gameoflife.app.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import hugo.weaving.DebugLog;
import pl.edu.agh.gameoflife.game.automaton.CellularAutomaton;
import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.event.PaintStructureWithBrush;
import pl.edu.agh.gameoflife.game.event.PaintWithBrush;
import pl.edu.agh.gameoflife.game.manager.GameParams;
import pl.edu.agh.gameoflife.game.structures.CrabStructure;
import pl.edu.agh.gameoflife.game.structures.DakotaStructure;
import pl.edu.agh.gameoflife.game.structures.FountainStructure;
import pl.edu.agh.gameoflife.game.structures.GliderStructure;
import pl.edu.agh.gameoflife.game.structures.GunStructure;
import pl.edu.agh.gameoflife.game.structures.PenthadecathlonStructure;
import pl.edu.agh.gameoflife.game.structures.SpaceshipStructure;
import pl.edu.agh.gameoflife.game.structures.Structure;
import pl.edu.agh.gameoflife.util.EventBus;

public class AutomatonView extends SurfaceView implements SurfaceHolder.Callback {
    private CellularAutomaton automaton;
    private GameParams params;
    private AutomatonThread thread;
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private static final int NONE = 0;
    private static final int PAN = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;

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

    void panZoomWithTouch(MotionEvent event){
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = PAN;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                break;

            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == PAN) {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
                }
                else if (mode == ZOOM) {
                    float[] f = new float[9];
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        if(checkScale(newDist, mid.x, mid.y)) {
                            matrix.set(savedMatrix);
                            float scale = newDist / oldDist;
                            matrix.postScale(scale, scale, mid.x, mid.y);

                            matrix.getValues(f);
                            float scaleX = f[Matrix.MSCALE_X];
                            float scaleY = f[Matrix.MSCALE_Y];

                            if(scaleX >= 8f) {
                                matrix.postScale((8f)/scaleX, (8f)/scaleY, mid.x, mid.y);
                            }

                        } else {
                            matrix.setTranslate(params.getDisplaySize().x / 2, params.getDisplaySize().y / 2);
                        }
                    }
                }
                rebound();
                break;

        }
        params.setMatrix(matrix);
        float[] f = new float[9];
        matrix.getValues(f);
        params.setMatrixScaleX(f[Matrix.MSCALE_X]);
        params.setMatrixScaleY(f[Matrix.MSCALE_Y]);
        params.setMatrixTransX(f[Matrix.MTRANS_X]);
        params.setMatrixTransY(f[Matrix.MTRANS_Y]);
    }

    private boolean checkScale(float newDist, float x, float y) {
        Matrix matrixTest = new Matrix();
        matrixTest.set(new Matrix(savedMatrix));
        float scaleTest = newDist / oldDist;
        matrixTest.postScale(scaleTest, scaleTest, x, y);

        RectF currentBounds = new RectF(0, 0, params.getDisplaySize().x, params.getDisplaySize().y);
        matrixTest.mapRect(currentBounds);

        RectF areaBounds = new RectF((float) getLeft(),
                (float) getTop(),
                (float) params.getDisplaySize().x + (float) getLeft(),
                (float) params.getDisplaySize().y + (float) getTop());

        if(currentBounds.left > areaBounds.left || currentBounds.top > areaBounds.top ||
                currentBounds.bottom < areaBounds.bottom || currentBounds.right < areaBounds.right) {
            return false;
        } else {
            return true;
        }
    }

    private void rebound() {
        RectF currentBounds = new RectF(0, 0, params.getDisplaySize().x, params.getDisplaySize().y);
        matrix.mapRect(currentBounds);

        RectF areaBounds = new RectF((float) getLeft(),
                (float) getTop(),
                (float) params.getDisplaySize().x + (float) getLeft(),
                (float) params.getDisplaySize().y + (float) getTop());

        PointF diff = new PointF(0f, 0f);

        if (currentBounds.width() > areaBounds.width()) {
            if (currentBounds.left > areaBounds.left) {
                diff.x = (areaBounds.left - currentBounds.left);
            }
            if (currentBounds.right < areaBounds.right) {
                diff.x = (areaBounds.right - currentBounds.right);
            }
        } else {
            diff.x = (areaBounds.left - currentBounds.left);
        }

        if (currentBounds.height() > areaBounds.height()) {
            if (currentBounds.top > areaBounds.top) {
                diff.y = (areaBounds.top - currentBounds.top);
            }
            if (currentBounds.bottom < areaBounds.bottom) {
                diff.y = (areaBounds.bottom - currentBounds.bottom);
            }
        } else {
            diff.y = (areaBounds.top - currentBounds.top);
        }

        matrix.postTranslate(diff.x, diff.y);
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(params.getIsZoom()) {
            panZoomWithTouch(event);
            invalidate();
        } else {
            paint(event);
        }

        return true;
    }

    protected void paint(MotionEvent event) {
        int x = Math.round(adjustX(event.getX()) / params.getCellSizeInPixels());
        int y = Math.round(adjustY(event.getY()) / params.getCellSizeInPixels());
        if(params.getStructure().equals("Point")) {
            EventBus.getInstance().post(new PaintWithBrush(x, y));
        } else if(params.getStructure().equals("Crab")) {
            paintStructure(new CrabStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        } else if(params.getStructure().equals("Dakota")) {
            paintStructure(new DakotaStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        } else if(params.getStructure().equals("Fountain")) {
            paintStructure(new FountainStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        } else if(params.getStructure().equals("Glider")) {
            paintStructure(new GliderStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        } else if(params.getStructure().equals("Gun")) {
            paintStructure(new GunStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        } else if(params.getStructure().equals("Penthadecathlon")) {
            paintStructure(new PenthadecathlonStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        } else if(params.getStructure().equals("Spaceship")) {
            paintStructure(new SpaceshipStructure(x, y, params.getGridSizeX(), params.getGridSizeY()));
        }
    }

    protected void paintStructure(Structure structure) {
        EventBus.getInstance().post(new PaintStructureWithBrush(structure));
    }

    private float adjustX(float x) {
        x /= params.getMatrixScaleX();
        x -= params.getMatrixTransX() / params.getMatrixScaleX();
        return x;
    }

    private float adjustY(float y) {
        y /= params.getMatrixScaleY();
        y -= params.getMatrixTransY() / params.getMatrixScaleY();
        return y;
    }
}
