package pl.edu.agh.gameoflife.game.event;

import pl.edu.agh.gameoflife.game.structures.Structure;

public class PaintStructureWithBrush {
    public final Structure structure;

    public PaintStructureWithBrush(Structure structure) {
        this.structure = structure;
    }
}
