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
}
