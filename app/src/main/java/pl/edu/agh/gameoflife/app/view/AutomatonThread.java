package pl.edu.agh.gameoflife.app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.squareup.otto.Subscribe;

import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.gameoflife.game.automaton.CellularAutomaton;
import pl.edu.agh.gameoflife.game.cell.Cell;
import pl.edu.agh.gameoflife.game.event.CellStateChange;
import pl.edu.agh.gameoflife.game.event.Draw;
import pl.edu.agh.gameoflife.game.event.PaintStructureWithBrush;
import pl.edu.agh.gameoflife.game.event.PaintWithBrush;
import pl.edu.agh.gameoflife.game.event.Pause;
import pl.edu.agh.gameoflife.game.event.Reset;
import pl.edu.agh.gameoflife.game.event.Resume;
import pl.edu.agh.gameoflife.game.event.Save;
import pl.edu.agh.gameoflife.game.event.Zoom;
import pl.edu.agh.gameoflife.game.grid.Grid;
import pl.edu.agh.gameoflife.game.manager.GameParams;
import pl.edu.agh.gameoflife.game.structures.Structure;
import pl.edu.agh.gameoflife.game.visualization.brush.Brush;
import pl.edu.agh.gameoflife.game.visualization.brush.DefaultBlockBrush;
import pl.edu.agh.gameoflife.game.visualization.cell.CellColors;
import pl.edu.agh.gameoflife.persistence.DataManager;
import pl.edu.agh.gameoflife.persistence.GridDaoRepository;
import pl.edu.agh.gameoflife.persistence.GridToGridDao;
import pl.edu.agh.gameoflife.util.EventBus;

class AutomatonThread extends Thread {
    private final CellularAutomaton automaton;
    private final SurfaceHolder surfaceHolder;
    private final CoordinateTranslator translator;
    private final Context context;
    private final List<CellStateChange> cellStateChanges;
    private final int cellSizeInPixels;
    private int timeForAFrame;
    private final CellColors cellColors;
    private final GameParams params;
    private boolean isRunning;
    private boolean shouldReset;
    private boolean shouldRestart;
    private boolean paused;
    private long cycleTime;
    private Bitmap buffCanvasBitmap;
    private Canvas buffCanvas;
    private Brush brush;
    private Structure structure;

    public AutomatonThread(CellularAutomaton automaton, SurfaceHolder surfaceHolder, GameParams params, Context context) {
        translator = new CoordinateTranslator(params.getScreenOrientation(), automaton.getSizeX(), automaton.getSizeY());
        cellStateChanges = new LinkedList<>();
        this.context = context;
        this.automaton = automaton;
        this.params = params;
        this.surfaceHolder = surfaceHolder;
        this.cellSizeInPixels = params.getCellSizeInPixels();
        timeForAFrame = 1000 / params.getFps();
        cellColors = params.getCellColors();
        brush = new DefaultBlockBrush();
        paused = params.startPaused();
        createBuffCanvas();
        initialDraw();
    }

    protected void createBuffCanvas() {
        final Point displaySize = params.getDisplaySize();
        buffCanvasBitmap = Bitmap.createBitmap(displaySize.x, displaySize.y, Bitmap.Config.ARGB_8888);
        buffCanvas = new Canvas();
        buffCanvas.setBitmap(buffCanvasBitmap);
    }

    private void initialDraw() {
        final Grid grid = automaton.getCurrentState();

        for (int j = 0; j < grid.getSizeY(); j++) {
            for (int i = 0; i < grid.getSizeX(); i++) {
                paintCell(i, j, grid.getCell(i, j).getState());
            }
        }
    }

    private void paintCell(int i, int j, int cellState) {
        Point p = translator.translate(new Point(i, j));
        int x = p.x;
        int y = p.y;

        Paint paint = cellColors.getPaint(cellState);
        Rect rect = new Rect(
                x * cellSizeInPixels,
                y * cellSizeInPixels,
                (x + 1) * cellSizeInPixels,
                (y + 1) * cellSizeInPixels
        );

        buffCanvas.drawRect(rect, paint);
    }

    @Subscribe
    synchronized public void onEvent(PaintWithBrush event) {
        final Point p = translator.reverseTranslate(new Point(event.x, event.y));
        brush.paint(automaton, p);
    }

    @Subscribe
    synchronized public void onEvent(PaintStructureWithBrush event) {
        structure = event.structure;
    }

    @Subscribe
    public void onEvent(CellStateChange cellStateChange) {
        synchronized (cellStateChanges) {
            cellStateChanges.add(cellStateChange);
        }
    }

    @Subscribe
    synchronized public void onEvent(Pause event) {
        paused = true;
    }

    @Subscribe
    synchronized public void onEvent(Resume event) {
        paused = false;
    }

    @Subscribe
    synchronized public void onEvent(Reset event) {
        shouldReset = true;
    }

    @Subscribe
    synchronized public void onEvent(Save event) {
        GridDaoRepository.save(DataManager.getSharedInstance(context).database,
                GridToGridDao.parse(automaton.getCurrentState(), event.saveName, params));
    }


  /*  @Subscribe
    synchronized public void onEvent(Load load) {
        //should be loaded from dialog
        List<GridDao> gridDaos = GridDaoRepository.getGrids(context);
        Collections.sort(gridDaos);
        List<Grid> grids = new LinkedList<>();
        for (GridDao gridDao : gridDaos) {
            grids.add(GridDaoToGrid.parse(gridDao));
        }

        if (grids.size() > 0) {
            automaton.fillFromGrid(grids.get(0));
        }

    }*/

    @Subscribe
    synchronized public void onEvent(Draw event) {
        params.setIsZoom(false);
    }

    @Subscribe
    synchronized public void onEvent(Zoom event) {
        params.setIsZoom(true);
    }

    public void setRunning(boolean v) {
        this.isRunning = v;
    }

    @Override
    public void run() {
        EventBus.getInstance().register(this);

        while (isRunning) {
            canvasCycle();
        }

        EventBus.getInstance().unregister(this);
    }

    protected void canvasCycle() {
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();
            gameCycle(canvas);

        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            unlockCanvasAndPost(canvas);
        }
    }

    protected void gameCycle(Canvas canvas) throws InterruptedException {
        if (canvas != null) {
            measuredCycleCore(canvas);
            sleepToKeepFps();
        }
    }

    protected void measuredCycleCore(Canvas canvas) {
        synchronized (surfaceHolder) {
            long t0 = System.currentTimeMillis();
            cycleCore(canvas);
            long t1 = System.currentTimeMillis();
            cycleTime = t1 - t0;
        }
    }

    private void cycleCore(Canvas canvas) {
        handleFlags();
        setTimeForAFrame();

        if (!paused) {
            stepAutomaton();
        }

        drawStructure();
        draw(canvas);
    }

    private void handleFlags() {
        handleReset();
        handleRestart();
        resetFlags();
    }

    private void setTimeForAFrame() {
        timeForAFrame = 1000 / params.getFps();
        if(params.getSlowerFaster()) {
            timeForAFrame /= params.getSpeedAnimation();
        } else {
            timeForAFrame *= params.getSpeedAnimation();
        }
    }

    private void handleReset() {
        if (shouldReset) {
            clearCanvas(automaton.getDefaultCellState());
            automaton.reset();
        }
    }

    private void handleRestart() {
        if (shouldRestart) {
            clearCanvas(automaton.getDefaultCellState());
            automaton.reset();
            automaton.randomFill(params.getGridCharacteristic());
        }
    }

    private void clearCanvas(int state) {
        final Point displaySize = params.getDisplaySize();
        Rect rect = new Rect(0, 0, displaySize.x, displaySize.y);
        Paint paint = cellColors.getPaint(state);
        buffCanvas.drawRect(rect, paint);
    }

    private void resetFlags() {
        shouldReset = false;
        shouldRestart = false;
    }

    private void drawStructure() {
        if(structure != null) {
            try {
                sleep(1000 / params.getFps());
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            Point p;
            for (Cell cell : structure.getListOfStructure()) {
                p = translator.reverseTranslate(new Point(cell.getX(), cell.getY()));
                brush.paint(automaton, p);
            }
            structure = null;
        }
    }

    private void stepAutomaton() {
        automaton.step(params.getStepAnimation());
    }

    private void draw(Canvas canvas) {
        LinkedList<CellStateChange> changes;

        synchronized (cellStateChanges) {
            changes = new LinkedList<>(this.cellStateChanges);
            cellStateChanges.clear();
        }

        for (CellStateChange change : changes) {
            paintCell(change.x, change.y, change.stateSnapshot);
        }

        canvas.drawBitmap(buffCanvasBitmap, params.getMatrix(), null);
    }

    private void sleepToKeepFps() throws InterruptedException {
        long sleepTime = timeForAFrame - cycleTime;

        if (sleepTime > 0) {
            sleep(sleepTime);
        }
    }

    private void unlockCanvasAndPost(Canvas canvas) {
        if (canvas != null) {
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private static class CoordinateTranslator {
        private final int screenOrientation;
        private final int automatonSizeX;
        private final int automatonSizeY;

        private CoordinateTranslator(int screenOrientation, int automatonSizeX, int automatonSizeY) {
            this.screenOrientation = screenOrientation;
            this.automatonSizeX = automatonSizeX;
            this.automatonSizeY = automatonSizeY;
        }

        private Point translate(Point p) {
            switch (screenOrientation) {
                case Surface.ROTATION_0:
                    return p;
                case Surface.ROTATION_90:
                    return new Point(p.y, automatonSizeX - p.x);
                case Surface.ROTATION_180:
                    return new Point(automatonSizeX - p.x, automatonSizeY - p.y);
                case Surface.ROTATION_270:
                    return new Point(automatonSizeY - p.y, p.x);

                default:
                    throw new AssertionError();
            }
        }

        private Point reverseTranslate(Point p) {
            switch (screenOrientation) {
                case Surface.ROTATION_0:
                    return p;
                case Surface.ROTATION_90:
                    return new Point(automatonSizeX - p.y, p.x);
                case Surface.ROTATION_180:
                    return new Point(automatonSizeY - p.x, automatonSizeX - p.y);
                case Surface.ROTATION_270:
                    return new Point(p.y, automatonSizeY - p.x);

                default:
                    throw new AssertionError();
            }
        }
    }
}
