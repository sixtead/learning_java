package ch2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class HelloComponent2 extends JComponent implements MouseMotionListener {

    private String theMessage;
    private int messageX = 125;
    private int messageY = 95;

    HelloComponent2(String message) {
        this.theMessage = message;
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
}
