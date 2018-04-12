package pl.edu.agh.gameoflife.game.rule;

public class RuleFactory {

    public static Rule createRuleByName(String rule) {

        if (rule.equals("Conways")) {
            return new ConwaysRule();
        }
        else if (rule.equals("Maze")) {
            return new MazeRule();
        }
        else if (rule.equals("Growth")) {
            return new GrowthRule();
        }
        else if (rule.equals("Flakes")) {
            return new FlakesRule();
        }
        else if (rule.equals("Coral")) {
            return new CoralRule();
        }
        else if (rule.equals("Cities surrounded by wall")) {
            return new CitiesSurroundedByWallRule();
        }
        else if (rule.equals("Replicator")) {
            return new ReplicatorRule();
        }
        else if (rule.equals("Seeds")) {
            return new SeedsRule();
        }
        else if (rule.equals("Serviette")) {
            return  new ServietteRule();
        }
        else if (rule.equals("Three four")) {
            return new ThreeFourRule();
        }
        else if (rule.equals("Assimilation")) {
            return new AssimilationRule();
        }
        else
            return null;
    }
    public static String getRuleByName(String rule){
        if (rule.equals("Conways")) {
            return new ConwaysRule().toString();
        }
        else if (rule.equals("Maze")) {
            return new MazeRule().toString();
        }
        else if (rule.equals("Growth")) {
            return new GrowthRule().toString();
        }
        else if (rule.equals("Flakes")) {
            return new FlakesRule().toString();
        }
        else if (rule.equals("Coral")) {
            return new CoralRule().toString();
        }
        else if (rule.equals("Cities surrounded by wall")) {
            return new CitiesSurroundedByWallRule().toString();
        }
        else if (rule.equals("Replicator")) {
            return new ReplicatorRule().toString();
        }
        else if (rule.equals("Seeds")) {
            return new SeedsRule().toString();
        }
        else if (rule.equals("Serviette")) {
            return  new ServietteRule().toString();
        }
        else if (rule.equals("Three four")) {
            return new ThreeFourRule().toString();
        }
        else if (rule.equals("Assimilation")) {
            return new AssimilationRule().toString();
        }
        else
            return null;
    }
    public static int getPositionByName(String rule){
        if (rule.equals("Conways")) {
            return 1;
        }
        else if (rule.equals("Maze")) {
            return 2;
        }
        else if (rule.equals("Growth")) {
            return 3;
        }
        else if (rule.equals("Flakes")) {
            return 4;
        }
        else if (rule.equals("Coral")) {
            return 5;
        }
        else if (rule.equals("Cities surrounded by wall")) {
            return 6;
        }
        else if (rule.equals("Replicator")) {
            return 7;
        }
        else if (rule.equals("Seeds")) {
            return 8;
        }
        else if (rule.equals("Serviette")) {
            return  9;
        }
        else if (rule.equals("Three four")) {
            return 10;
        }
        else if (rule.equals("Assimilation")) {
            return 11;
        }
        else
            return 0;
    }
}
