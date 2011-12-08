/**
 * 
 */
package pentaclone.presentation.swing;

/**
 * Interface to rely information from the rotation panel to the main GUI 
 * @author Diego
 *
 */
public interface IRotationReporter {
	
	public static final char LEFT = 'l';
	
	public static final char RIGHT = 'r';
	
	public static final char SKIP = 's';
	
	/**
	 * Reports a rotation instruction
	 * @param blockNum the block number (1-4, left to right, top to bottom)
	 * @param direction LEFT, RIGHT or SKIP (rotation)
	 */
	public boolean rotate(int blockNum, char direction);

}
