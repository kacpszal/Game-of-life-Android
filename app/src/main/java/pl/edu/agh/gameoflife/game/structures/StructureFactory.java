package pl.edu.agh.gameoflife.game.structures;


public class StructureFactory {
    public static int getPositionByName(String structureName) {
        if (structureName.equals("Crab")) {
            return 1;
        } else if (structureName.equals("Dakota")) {
            return 2;
        } else if (structureName.equals("Fountain")) {
            return 3;
        } else if (structureName.equals("Glider")) {
            return 4;
        } else if (structureName.equals("Gun")) {
            return 5;
        } else if (structureName.equals("Penthadecathlon")) {
            return 6;
        } else if (structureName.equals("Spaceship")) {
            return 7;
        }else
            return 0;
    }

}
