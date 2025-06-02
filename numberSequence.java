import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.awt.event.*;
import java.io.File;
import java.lang.reflect.Array;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.text.PlainDocument;

import multipliers.*;
import rules.rule;
import rules.rulePool;

import java.util.*;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class numberSequence extends JPanel implements KeyListener {
    private String input;
    private boolean gameStart = true;

    private ArrayList<Integer> generatedNumbers;
    private ArrayList<Integer> numbersInput;
    private ArrayList<Integer> numberPool;
    private ArrayList<Character> operations;

    private ArrayList<Integer> indexes;
    
    private int total;
    private int targetNum;
    private int lives = 5;
    private int score;
    private int bonus = 0;
    private int poolSize = 4;   //default to 4
    private int round = 1;

    private ArrayList<multiplier> activeMultipliers;
    private multipliersPool mPool;

    private rule currentRule;
    private rulePool rPool;

    private Image instructions;
    
    public numberSequence(){
        File file = new File("instructions.png");
        
        try {
            this.instructions = ImageIO.read(file);
        } catch (Exception e){}

        this.input = "";
        
        this.generatedNumbers = generateNumberList();
        this.total = 0;
        this.targetNum = newTarget();
        this.score = 0;
        
        this.numberPool = new ArrayList<>(generatedNumbers);
        this.numbersInput = new ArrayList<Integer>();
        this.operations = new ArrayList<Character>();

        this.indexes = new ArrayList<>();

        this.activeMultipliers = new ArrayList<multiplier>();
        this.mPool = new multipliersPool();

        this.currentRule = new rule("", "");
        this.rPool = new rulePool();
    }

    private ArrayList<Integer> generateNumberList(){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int curr;
        int count = 1;

        for(int i = 0; i < poolSize; i++){
            curr = (int)(Math.random() * 10);
            if(temp.contains(curr)){
                count++;
                if(count == 3){
                    do {
                        curr = (int)(Math.random() * 10);
                    }
                    while(temp.contains(curr));
                }
            }
            temp.add(curr);
        }

        return temp;
    }

    private void reset(){
        generatedNumbers = generateNumberList();
        numberPool = new ArrayList<>(generatedNumbers);
        numbersInput.clear();
        indexes.clear();
        operations.clear();
        input = "";
        total = 0;
        bonus = 0;
    }

    private int newTarget(){
        return (int)(Math.random() * 9) + 10;
    }

    public boolean isOperation(char c){
        ArrayList<Character> symbols = new ArrayList<Character>();
        symbols.add('+');
        symbols.add('-');
        symbols.add('*');
        symbols.add('x');
        symbols.add('/');
        symbols.add('÷');

        return symbols.contains(c);
    }

    public int calculateTotal(ArrayList<Integer> numbers, ArrayList<Character> ops){
        int total = 0;

        total = numbers.get(0);
        for(int i = 0; i < ops.size(); i++){
            if(ops.get(i) == '+'){
                total += numbers.get(i+1);
            }
            else if(ops.get(i) == '-'){
                total -= numbers.get(i+1);
            }
            else if(ops.get(i) == '*'){
                total *= numbers.get(i+1);
            }
            else if(ops.get(i) == '/'){
                try {
                    if(total % numbers.get(i+1) == 0){
                        total /= numbers.get(i+1);
                    }
                } catch(ArithmeticException e) {}
            }
        }

        return total;
    }

    public void newMultiplier(){
        multiplier x;
        
        do{
            x = mPool.getRandomMultiplier();
        }
        while(activeMultipliers.contains(x));

        activeMultipliers.add(x);
    }

    public void newRule(){
        rule x = rPool.getRandomRule();
        if(!currentRule.equals(x) && !currentRule.getActiveStatus()) {
            currentRule = x;
        }
    }

    public int increaseScore(ArrayList<Integer> numbersInputted, int bonus){
        int sum = 25 * (numbersInputted.size() + bonus);    // raw score (25pts/number)
        int rulePenalty = 0;
        int multipliersBonus = 0;

        if(currentRule.getActiveStatus()){
            rulePenalty = currentRule.applyRule(sum, numbersInputted, operations);
        }

        if(activeMultipliers.size() > 0){
            for(multiplier x : activeMultipliers){
                multipliersBonus = multipliersBonus + x.calculateBonus(sum, numbersInputted, operations, currentRule);
            }
        }

        sum = sum + rulePenalty + multipliersBonus;

        return sum;
    }

    // VOID FUNCTION THAT KEEPS PROGRAMMING INFINITELY RUNNING
    public int run(){
        return lives;
    }

    // KEY TYPED
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();

        // TAKES CHARACTER TYPED & ADDS TO INPUT. IF TYPED A NUMBER -> ADDED TO NUMBER POOL, ELSE IF AN OPERATOR -> ADDED TO OPERATOR POOL.
        if(generatedNumbers.contains(Character.getNumericValue(c)) && numberPool.contains(Character.getNumericValue(c)) && (input.length() == 0 || isOperation(input.charAt(input.length() - 1)))){
            input += c;

            if(numbersInput.contains(Character.getNumericValue(c))){
                indexes.add(generatedNumbers.lastIndexOf(Character.getNumericValue(c)));
            }
            else {
                indexes.add(generatedNumbers.indexOf(Character.getNumericValue(c)));
            }

            numberPool.remove((Object)Character.getNumericValue(c));
            numbersInput.add(Character.getNumericValue(c));

        }
        else if(isOperation(c) && input.length() >= 1){
            if(!isOperation(input.charAt(input.length() - 1))){
                if(c == '/'){
                    input += '÷';
                }
                else if(c == '*'){
                    input += 'x';
                }
                else {
                    input += c;
                }
                operations.add(c);
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();

        // BACKSPACE - DELETES LAST CHARACTER INPUT
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            if (input.length() > 0) {
                if(!isOperation(input.charAt(input.length() - 1))){
                    numberPool.add(numbersInput.remove(numbersInput.size()-1));
                    indexes.remove(indexes.size()-1);
                }
                else {
                    operations.remove(operations.size()-1);
                }

                input = input.substring(0, input.length() - 1);
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){   // PERFORMS CALCULATION. IF HIT TARGET -> NEXT ROUND, ELSE CALCULATES TWO NUMBER INPUT
            if(total == targetNum){
                score += increaseScore(numbersInput, bonus);
                
                if((round > 0 && round % 7 == 0) && activeMultipliers.size() < 3){
                    newMultiplier();
                }

                if(round % 5 == 0){
                    newRule();
                    currentRule.setActiveStatus(true);
                }
                else if(currentRule.getActiveStatus() == true){
                    currentRule.setActiveStatus(false);
                }

                if(round % 7 == 0){
                    if(lives < 7){
                        lives++;
                    }
                }

                round++;
                
                reset();
                targetNum = newTarget();
            }
            else if(numbersInput.size() == 2){
                generatedNumbers = new ArrayList<>(numberPool);
                generatedNumbers.add(total);
                numberPool = new ArrayList<>(generatedNumbers);

                numbersInput.clear();
                operations.clear();
                input = "";
                total = 0;
                bonus++;
                if(bonus > 2) bonus = 2;

                indexes.clear();

                if(numberPool.size() <= 1){
                    reset();
                    targetNum = newTarget();
                }
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE){  // CLEAR INPUT FIELD
            input = "";
            numberPool = new ArrayList<>(generatedNumbers);

            numbersInput.clear();
            operations.clear();
            total = 0;
            indexes.clear();
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE && gameStart){
            gameStart = false;
        }
        
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == 'r' || c == 'R') {
            reset();
            lives--;
            if(lives < 0){
                lives = 0;
            }
        }
        else if (c == 'n' || c == 'N') {
            reset();
            targetNum = newTarget();
            lives -= 2;
        }

        if(numbersInput.size() >= 2 && numbersInput.size() > operations.size()){
            total = calculateTotal(numbersInput, operations);
        }
        else if(numbersInput.size() == 1){
            total = numbersInput.get(0);
        }
        else if(numbersInput.size() == 0){
            total = 0;
        }

        repaint();
    }

    // PAINTS SCREEN AND TEXT
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        int x = 75;
        int y = 100;

        // background
        if(currentRule.getActiveStatus() == false){
            g2.setColor(new Color(11, 24, 54));
        }
        else {
            g2.setColor(new Color(54, 11, 11));
        }
        g2.fillRect(0, 0, 1000, 800);

        // SCORE & SCORE BAR
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, 1000, 50);
        g2.setColor(new Color(61, 0, 255));
        g2.fillRect(0, 0, score*2, 50);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Verdana", Font.BOLD, 30));
        g2.drawString(score + "", 825, 35);
        
        if(gameStart){
            g2.drawImage(instructions, 100, 50, null);
        }
        else {
            // DRAWS TARGET NUMBER
            g2.fillRect(810, 80, 140, 135);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Verdana", Font.BOLD, 27));
            g2.drawString("TARGET", 820, 110);
            g2.setFont(new Font("Verdana", Font.BOLD, 85));
            g2.setColor(Color.RED);
            g2.drawString(targetNum + "", 817, 195);

            
            // DRAWS GENERATED NUMBERS
            x = 345 - (15*(poolSize-4));
            for(int i = 0; i < generatedNumbers.size(); i++){
                if(indexes.contains(i)){
                    g2.setColor(Color.GRAY);
                    g2.fillRect(x-2, 87, 64, 90);
                }

                g2.setColor(Color.WHITE);
                g2.drawString(generatedNumbers.get(i) + " ", x, 160);
                x += 75;
            }


            // DRAWS INPUT
            g2.setColor(Color.GRAY);
            g2.fillRect(65, 295, 857, 150);
            g2.setColor(Color.BLACK);
            g2.fillRect(75, 305, 837, 130);
            
            x = 530 - (50*(numbersInput.size() + operations.size()));
            g2.setFont(new Font("Metro Time Sign", Font.PLAIN, 100));
            for(int i = 0; i < input.length(); i++){
                if(isOperation(input.charAt(i))){
                    g2.setColor(Color.RED);
                }
                else {
                    g2.setColor(Color.ORANGE);
                }
                g2.drawString(input.charAt(i) + " ", x, 420);
                x += 95;
            }

            // DRAWS OUTPUT
            g2.setColor(Color.BLACK);
            g2.fillRect(762, 445, 150, 85);
            g2.setFont(new Font("Metro Time Sign", Font.BOLD, 55));
            g2.setColor(Color.WHITE);
            g2.drawString("= " + total + "", 770, 515);

            // DRAWS ROUND NUMBER
            g2.setFont(new Font("Verdana", Font.PLAIN, 27));
            g2.drawString("Round " + round, 50, 670);

            // DRAWS LIVES
            x = 50;
            g2.setColor(Color.GREEN);
            for(int i = 0; i < lives; i++){
                g2.fillOval(x, 700, 35, 35);
                x += 50;
            }

            // DRAWS MULTIPLIERS
            y = 670;
            if(activeMultipliers.size() > 0){
                for(multiplier m : activeMultipliers){
                    m.paint(g2, 670, y);
                    y += 30;
                }
            }

            // DRAWS RULE
            if(currentRule.getActiveStatus()){
                currentRule.paint(g2, 50, 150);
            }
        }
    }
}
