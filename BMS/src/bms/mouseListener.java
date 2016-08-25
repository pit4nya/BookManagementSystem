package bms;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

abstract class mouseListener extends MouseAdapter {
	abstract public void mouseClicked(MouseEvent e);
}