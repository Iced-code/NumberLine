package rules;
import java.awt.*;
import java.util.*;


public class rule {
    private String name;
    private String description;
    private boolean isActive;

    public rule(String name, String desc){
        this.name = name;
        this.description = desc;
        this.isActive = false;
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public boolean getActiveStatus(){
        return isActive;
    }

    public void setName(String aName){
        name = aName;
    }
    public void setDescription(String aDesc){
        description = aDesc;
    }
    public void setActiveStatus(boolean newStatus){
        isActive = newStatus;
    }
    public void setActiveStatus(){
        isActive = !isActive;
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
        return getName() + "\n" + getDescription();
    }

    public int applyRule(int score, ArrayList<Integer> numbers, ArrayList<Character> operators){
        return -1;
    }

    public void paint(Graphics2D g, int x, int y){
        g.setColor(Color.RED);
        g.setFont(new Font("Verdana", Font.BOLD, 30));
        g.drawString(getName(), x, y);

        g.setFont(new Font("Verdana", Font.PLAIN, 22));
        g.drawString(getDescription(), x, y+40);
    }
}
