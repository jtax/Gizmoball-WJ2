package controller;

import model.Gizmo;
import model.IElement;
import model.Triggerable;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;

/**
 * Package: controller Created by Laurynas Sakalauskas on 17/02/16 13:39.
 * Project: Gizmoball-WJ2
 */
public class KeyPressListener implements KeyListener {

	private final Collection<IElement> elements;

	public KeyPressListener(Collection<IElement> elements) {

		this.elements = elements;
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
		for (IElement element : elements) {

			if (e.getKeyCode() == ((Gizmo) element).getKeyPressTrigger()) {

				if (element instanceof Triggerable) {

					((Triggerable) element).keyTrigger();

				}
			}
		}
	}
}
