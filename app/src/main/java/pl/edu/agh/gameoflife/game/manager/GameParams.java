package pl.edu.agh.gameoflife.game.manager;

import android.graphics.Point;

import pl.edu.agh.gameoflife.game.automaton.GridCharacteristic;
import pl.edu.agh.gameoflife.game.visualization.cell.CellColors;

public class GameParams {
    private final int screenOrientation;
    private final Point displaySize;
    private int gridSizeX;
    private int gridSizeY;
    private final int cellSizeInPixels;
    private final GridCharacteristic gridCharacteristic;
    private final CellColors cellColors;
    private final int fps;
    private final boolean startPaused;

    private GameParams(Builder builder) {
        screenOrientation = builder.screenOrientation;
        displaySize = builder.displaySize;
        gridSizeX = builder.gridSizeX;
        gridSizeY = builder.gridSizeY;
        cellSizeInPixels = builder.cellSizeInPixels;
        gridCharacteristic = builder.gridCharacteristic;
        cellColors = builder.cellColors;
        fps = builder.fps;
        startPaused = builder.startPaused;
    }

    public int getScreenOrientation() {
        return screenOrientation;
    }

    public Point getDisplaySize() {
        return displaySize;
    }

    public int getGridSizeX() {
        return gridSizeX;
    }

    public int getGridSizeY() {
        return gridSizeY;
    }

    public int getCellSizeInPixels() {
        return cellSizeInPixels;
    }

    public GridCharacteristic getGridCharacteristic() {
        return gridCharacteristic;
    }

    public CellColors getCellColors() {
        return cellColors;
    }

    public int getFps() {
        return fps;
    }

    public boolean startPaused() {
        return startPaused;
    }

    public static final class Builder {
        private int screenOrientation;
        private final Point displaySize;
        private int gridSizeX;
        private int gridSizeY;
        private final int cellSizeInPixels;
        private GridCharacteristic gridCharacteristic;
        private CellColors cellColors;
        private int fps;
        private boolean startPaused;

        public Builder(Point displaySize, int cellSizeInPixels) {
            this.displaySize = displaySize;
            this.cellSizeInPixels = cellSizeInPixels;
            this.gridSizeX = displaySize.x / cellSizeInPixels;
            this.gridSizeY = displaySize.y / cellSizeInPixels;
        }

        public Builder withScreenOrientation(int screenOrientation) {
            this.screenOrientation = screenOrientation;
            return this;
        }

        public Builder withGameCharacteristic(GridCharacteristic gridCharacteristic) {
            this.gridCharacteristic = gridCharacteristic;
            return this;
        }

        public Builder withCellColors(CellColors cellColors) {
            this.cellColors = cellColors;
            return this;
        }

        public Builder withFps(int fps) {
            this.fps = fps;
            return this;
        }

        public Builder withStartPaused(boolean startPaused) {
            this.startPaused = startPaused;
            return this;
        }

        public Builder withGridSizeX(int val) {
            gridSizeX = val;
            return this;
        }

        public Builder withGridSizeY(int val) {
            gridSizeY = val;
            return this;
        }

        public GameParams build() {
            return new GameParams(this);
        }
    }
}
