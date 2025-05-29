package multipliers;

import java.util.*;

import rules.*;


public class multiplier_multiplicationPlus extends multiplier{

    public multiplier_multiplicationPlus(){
        super("multiplication+", 15, '+');
    }

    public int calculateBonus(int score, ArrayList<Integer> numbers, ArrayList<Character> operators, rule activeRule) {
        return (getNumberBonus() * (getCharCount(operators, '*')));
    }
}
