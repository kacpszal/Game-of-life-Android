package pl.edu.agh.gameoflife.game.automaton;

public class GridCharacteristic {
    private float cellPercentage;
    private int cellState;
    private float rectangleScale = 1.0f;

    public GridCharacteristic(float cellPercentage, int cellState) {
        this.cellPercentage = cellPercentage;
        this.cellState = cellState;
    }

    public float getCellPercentage() {
        return cellPercentage;
    }

    public int getCellState() {
        return cellState;
    }

    public float getRectangleScale() {
        return rectangleScale;
    }

    public void setCellPercentage(float cellPercentage) {
        this.cellPercentage = cellPercentage;
    }

    public void setCellState(int cellState) {
        this.cellState = cellState;
    }

    public void setRectangleScale(float rectangleScale) {
        this.rectangleScale = rectangleScale;
    }
}
