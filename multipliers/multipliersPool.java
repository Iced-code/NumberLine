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


public class multipliersPool {
    private ArrayList<multiplier> multipliers;
    private int size;

    public multipliersPool(){
        this.multipliers = new ArrayList<multiplier>();
        loadMultiplierPool();
        this.size = multipliers.size();
    }

    private void loadMultiplierPool(){
        multipliers.add(new multiplier_additionPlus());
        multipliers.add(new multiplier_subtractionPlus());
        multipliers.add(new multiplier_multiplicationPlus());
        multipliers.add(new multiplier_divisionPlus());
        multipliers.add(new multiplier_fourAndBelow());

        /* multipliers.add(new multiplier("Addition+", 10, '+'));
        multipliers.add(new multiplier("Subtraction+", 20, '+'));
        multipliers.add(new multiplier("Multiplication+", 15, '*'));
        multipliers.add(new multiplier("Division+", 25, '/')); */
    }

    public multiplier getRandomMultiplier(){
        return multipliers.get((int)(Math.random() * size));
    }
}
