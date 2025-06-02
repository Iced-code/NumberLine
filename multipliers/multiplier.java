package multipliers;
import java.awt.*;
import java.util.*;
import rules.*;


public abstract class multiplier {
    private String name;
    private int numberBonus;
    private char multiplierType;

    public multiplier(String name, int numberBonus, char type){
        this.name = name;
        this.numberBonus = numberBonus;
        this.multiplierType = type;
    }

    public String getName(){
        return name;
    }
    public int getNumberBonus(){
        return numberBonus;
    }
    public char getMultiplierType(){
        return multiplierType;
    }

    public void setName(String aName){
        name = aName;
    }
    public void setNumberBonus(int newBonus){
        numberBonus = newBonus;
    }
    public void setMultiplierType(char newType){
        multiplierType = newType;
    }

    public int getNumCount(ArrayList<Integer> list, Integer i){
        int count = 0;

        for(Integer curr : list){
            if(curr.equals(i)){
                count++;
            }
        }

        return count;
    }

    public int getCharCount(ArrayList<Character> list, Character c){
        int count = 0;

        for(Character curr : list){
            if(curr.equals(c)){
                count++;
            }
        }

        return count;
    }

    public String flavorText() {
        return getName() + " (" + getMultiplierType() + getNumberBonus() + ")";
    }

    public abstract int calculateBonus(int score, ArrayList<Integer> numbers, ArrayList<Character> operators, rule activeRule);

    public void paint(Graphics2D g, int x, int y){
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Verdana", Font.PLAIN, 22));
        g.drawString(flavorText(), x, y);
    }
}
