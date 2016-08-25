package bms;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class myListener {
}

abstract class mouseListener extends MouseAdapter {
	abstract public void mouseClicked(MouseEvent e);
}

abstract class keyListener extends KeyAdapter {
	abstract public void keyPressed(KeyEvent e);
}