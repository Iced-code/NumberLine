import javax.swing.*;

// MAIN PROGRAM EXECUTER
public class sequenceRunner extends numberSequence {
    public static void main(String[] args) {
        /* SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Number Line");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 800);
            frame.setLocation(350, 50);
            frame.setLayout(null);

            numberSequence sequence = new numberSequence();
            sequence.setSize(1210, 805);
            sequence.setLocation(0, 0);

            frame.getContentPane().add(sequence);
            frame.addKeyListener(sequence);

            frame.setVisible(true);

            // Run sequence.run() repeatedly on a timer instead of an infinite loop
            Timer timer = new Timer(100, e -> sequence.run());  // ~60 FPS
            timer.start();
        }); */

        JFrame frame = new JFrame("Number Line");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocation(350, 50);
        frame.setLayout(null);

        numberSequence sequence = new numberSequence();
        sequence.setSize(1210, 805);
        sequence.setLocation(0, 0);

        frame.getContentPane().add(sequence);
        frame.addKeyListener(sequence);

        frame.setVisible(true);
    }
}
