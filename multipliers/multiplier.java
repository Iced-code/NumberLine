package multipliers;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.text.PlainDocument;

import java.util.*;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


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

    public abstract int calculateBonus(int score, ArrayList<Integer> numbers, ArrayList<Character> operators);
}
