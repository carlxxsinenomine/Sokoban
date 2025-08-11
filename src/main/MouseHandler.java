package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    public int mouseX = 0;
    public int mouseY = 0;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseX = mouseY = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {} // might use this later for animation purposes

    @Override
    public void mouseExited(MouseEvent e) {}
}
