package multipliers;

import java.util.*;

import rules.rule;


public class multiplier_additionPlus extends multiplier{

    public multiplier_additionPlus(){
        super("addition+", 10, '+');
    }

    public int calculateBonus(int score, ArrayList<Integer> numbers, ArrayList<Character> operators, rule activeRule) {
        return (getNumberBonus() * getCharCount(operators, '+'));
    }
}
