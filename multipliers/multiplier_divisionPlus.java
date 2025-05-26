package multipliers;

import java.util.*;


public class multiplier_divisionPlus extends multiplier{

    public multiplier_divisionPlus(){
        super("division+", 25, '+');
    }

    public int calculateBonus(int score, ArrayList<Integer> numbers, ArrayList<Character> operators) {
        return (getNumberBonus() * getCharCount(operators, '/'));
    }
}
