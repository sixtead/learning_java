package ch2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class HelloComponent3 extends JComponent implements MouseMotionListener, ActionListener {

    private String theMessage;
    private int messageX = 125;
    private int messageY = 95;
    private int colorIndex;
    private JButton theButton;
    private static Color[] someColors = {Color.black, Color.red, Color.green, Color.blue, Color.magenta};

    HelloComponent3(String message) {
        this.theMessage = message;
        this.theButton = new JButton("Change Color");
        setLayout(new FlowLayout());
        add(theButton);
        theButton.addActionListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
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
}
