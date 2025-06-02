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

    @Override
    public void paint(Graphics2D g, int x, int y){
        g.setColor(Color.RED);
        g.setFont(new Font("Verdana", Font.BOLD, 30));
        g.drawString(getName(), x, y);

        g.setFont(new Font("Verdana", Font.PLAIN, 22));
        g.drawString("Numbers less than 5", x, y+40);
        g.drawString("do not give points.", x, y+65);
    }
}
