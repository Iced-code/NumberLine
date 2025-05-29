package rules;
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


public class rulePool {
    private ArrayList<rule> rules;
    private int size;

    public rulePool(){
        this.rules = new ArrayList<rule>();
        loadRulePool();
        this.size = rules.size();
    }

    private void loadRulePool(){
        rules.add(new rule_higherScores());
    }

    public rule getRandomRule(){
        return rules.get((int)(Math.random() * size));
    }
}
