package rules;

import java.util.*;
import java.awt.*;


public class rule_higherScores extends rule{

    public rule_higherScores(){
        super("Higher Scores", "Numbers less than 5 do not give points.");
    }

    public int applyRule(int score, ArrayList<Integer> numbers, ArrayList<Character> operators) {
        int minus = 0;

        for(Integer i : numbers){
            if(i < 5){
                minus -= 25;
            }
        }

        return minus;
    }
}
