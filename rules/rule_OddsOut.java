package rules;
import java.util.*;
import java.awt.*;


public class rule_OddsOut extends rule {

    public rule_OddsOut(){
        super("Odds Out", "Odd numbers do not give points.");
    }

    public int applyRule(int score, ArrayList<Integer> numbers, ArrayList<Character> operators) {
        int minus = 0;

        for(Integer i : numbers){
            if(i % 2 == 1){
                minus -= 25;
            }
        }

        return minus;
    }

    @Override
    public void paintComponent(Graphics2D g, int x, int y){
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.setFont(new Font("Verdana", Font.BOLD, 30));
        g.drawString(getName(), x, y);

        g.setFont(new Font("Verdana", Font.PLAIN, 22));
        g.drawString("Odd numbers do", x, y+40);
        g.drawString("not give points.", x, y+65);
    }
}
