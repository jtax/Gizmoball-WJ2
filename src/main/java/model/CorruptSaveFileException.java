package model;

/**
 * Thrown when a save file is corrupt.
 * 
 * @author Team WJ2
 *
 */
public class CorruptSaveFileException extends Exception {

	/**
	 * We won't serialise it anyway.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Make a new corrupt save file exception with the message error
	 * 
	 * @param error the message
	 */
	public CorruptSaveFileException(String error) {

	}

}