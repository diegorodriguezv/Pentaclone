/**
 * 
 */
package pentaclone.presentation.swing;

/**
 * @author Diego
 * 
 */
public interface IBlockReporter {

	/**
     * The grid was modified at the location (row, col)
     * 
     * @param blockNum
     * @param row
     * @param col
     */
	public void blockClickedAt(int blockNum, int row, int col);

}
