package multipliers;

import java.util.*;
import rules.*;


public class multiplier_subtractionPlus extends multiplier{

    public multiplier_subtractionPlus(){
        super("subtraction+", 20, '+');
    }

    public int calculateBonus(int score, ArrayList<Integer> numbers, ArrayList<Character> operators, rule activeRule) {
        return (getNumberBonus() * getCharCount(operators, '-'));
    }
}
