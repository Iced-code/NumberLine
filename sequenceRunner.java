import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


// MAIN PROGRAM EXECUTER
public class sequenceRunner extends numberSequence {
    public static void main(String[] args) {

        //makes the game window
        JFrame frame = new JFrame("reun");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 800);
        frame.setLocation(350, 50);
        frame.setLayout(null);

        //makes the game
        numberSequence sequence = new numberSequence();
        sequence.setSize(1210, 805);
        sequence.setLocation(0, 0);

        frame.getContentPane().add(sequence);

        frame.setVisible(true);

        frame.addKeyListener(sequence);

        while(true){
            sequence.run();
        }

        
        /* ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> mytTerminal.repaint();
        // Schedule the task to run every 2 seconds with no initial delay
        executor.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS); */
    }
}
