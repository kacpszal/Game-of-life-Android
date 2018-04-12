package pl.edu.agh.gameoflife.game.neighborhood;


public class NeighborhoodFactory {
    public static int getPositionByName(String neighborhood){
        if(neighborhood.equals("Moore")){
            return 0;
        }else
            return 1;
    }
}
