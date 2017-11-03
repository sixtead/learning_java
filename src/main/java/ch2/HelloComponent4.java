package ch2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class HelloComponent4 extends JComponent
        implements MouseMotionListener, ActionListener, Runnable {

    private String theMessage;
    private int messageX = 125;
    private int messageY = 95;
    private JButton theButton;
    private int colorIndex;
    private static Color[] someColors = {Color.black, Color.red, Color.green, Color.blue, Color.magenta};
    private boolean blinkState;

    HelloComponent4(String message) {
        this.theMessage = message;
        this.theButton = new JButton("Change Color");
        this.setLayout(new FlowLayout());
        this.add(theButton);
        theButton.addActionListener(this);
        this.addMouseMotionListener(this);
        Thread t = new Thread(this);
        t.start();
    }

    public void paintComponent(Graphics g) {
        g.setColor(blinkState ? getBackground() : currentColor());
        g.drawString(theMessage, messageX, messageY);
    }

    public void mouseDragged(MouseEvent e) {
        messageX = e.getX();
        messageY = e.getY();
        repaint();
    }

    public void mouseMoved(MouseEvent e) {}

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == theButton) {
            changeColor();
        }
    }

    synchronized private void changeColor() {
        if(++colorIndex == someColors.length) {
            colorIndex = 0;
        }
        setForeground(currentColor());
        repaint();
    }

    synchronized private Color currentColor() {
        return someColors[colorIndex];
    }

    public void run() {
        try {
            while (true) {
                blinkState = !blinkState;
                repaint();
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {}
    }
}
