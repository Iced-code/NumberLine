package rules;
import java.util.*;


public class rulePool {
    private ArrayList<rule> rules;
    private int size;

    public rulePool(){
        this.rules = new ArrayList<rule>();
        loadRulePool();
        this.size = rules.size();
    }

    private void loadRulePool(){
        rules.add(new rule_HigherScores());
        rules.add(new rule_OddsOut());
    }

    public rule getRandomRule(){
        return rules.get((int)(Math.random() * size));
    }
}
