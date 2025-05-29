package multipliers;

import java.util.*;

import rules.rule;


public class multiplier_divisionPlus extends multiplier{

    public multiplier_divisionPlus(){
        super("division+", 25, '+');
    }

    public int calculateBonus(int score, ArrayList<Integer> numbers, ArrayList<Character> operators, rule activeRule) {
        return (getNumberBonus() * getCharCount(operators, '/'));
    }
}
