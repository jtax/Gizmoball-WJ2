package controller;

import model.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;


 /**
 * Gizmoball - BoardMouseListener
 *
 * Description: KeyPressListener deals with key presses.
 *
 * Created by Group WJ2 on 08/03/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class KeyPressListener implements KeyListener {

	private Collection<IElement> elements;
	private IBoard board;

	public KeyPressListener(IBoard board) {
		this.board = board;
		this.elements = board.getElements();
	}

	/**
	 * Invoked when a key has been typed. See the class description for
	 * {@link KeyEvent} for a definition of a key typed event.
	 *
	 * @param e
	 */
	@Override
	public void keyTyped(KeyEvent e) {

		//

	}

	/**
	 * Invoked when a key has been pressed. See the class description for
	 * {@link KeyEvent} for a definition of a key pressed event.
	 *
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		handleTrigger(e);
	}

	/**
	 * Invoked when a key has been released. See the class description for
	 * {@link KeyEvent} for a definition of a key released event.
	 *
	 * @param e
	 */
	@Override
	public void keyReleased(KeyEvent e) {

		handleTrigger(e);

	}

	/**
	 * Execute trigger on keyPress and keyReleased action
	 *
	 * @param e
	 */
	private void handleTrigger(KeyEvent e) {
		elements = board.getElements();
		for (IElement element : elements) {

			if (e.getKeyCode() == ((Gizmo) element).getKeyPressTrigger()) {

				if (element instanceof Triggerable) {

					((Triggerable) element).trigger();

				}
			}
		}
	}
}
