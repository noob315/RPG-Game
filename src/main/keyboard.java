package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboard implements KeyListener {
    public boolean upside, downside, leftside, rightside;

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upside = true;
        }
        if (code == KeyEvent.VK_S) {
            downside = true;
        }
        if (code == KeyEvent.VK_A) {
            leftside = true;
        }
        if (code == KeyEvent.VK_D) {
            rightside = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upside = false;
        }
        if (code == KeyEvent.VK_S) {
            downside = false;
        }
        if (code == KeyEvent.VK_A) {
            leftside = false;
        }
        if (code == KeyEvent.VK_D) {
            rightside = false;
        }
    }

}
