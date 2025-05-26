package multipliers;

import java.util.*;


public class multiplier_fourAndBelow extends multiplier{

    public multiplier_fourAndBelow(){
        super("Four & Below", 2, 'x');
    }

    public int calculateBonus(int score, ArrayList<Integer> numbers, ArrayList<Character> operators) {
        int count = 0;

        for(Integer curr : numbers){
            if(curr <= 4){
                count++;
            }
        }

        return (count * 25);
    }
}
