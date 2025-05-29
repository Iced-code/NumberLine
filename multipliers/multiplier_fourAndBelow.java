package multipliers;

import java.util.*;

import rules.*;


public class multiplier_fourAndBelow extends multiplier{

    public multiplier_fourAndBelow(){
        super("Four & Below", 2, 'x');
    }

    public int calculateBonus(int score, ArrayList<Integer> numbers, ArrayList<Character> operators, rule activeRule) {
        int count = 0;

        if(activeRule.getName().equals("Higher Scores") && activeRule.getActiveStatus() == true){
            return count;
        }

        for(Integer curr : numbers){
            if(curr <= 4){
                count++;
            }
        }

        return (count * 25);
    }
}
