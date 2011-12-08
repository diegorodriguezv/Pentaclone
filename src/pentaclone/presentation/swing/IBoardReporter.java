/**
 * 
 */
package pentaclone.presentation.swing;

/**IBoardReporter
 * Interface to rely information from the BoardPanel to the main GUI 
 * @author Diego
 *
 */
public interface IBoardReporter {
    
    /**
     * The grid was modified at the location (row, col)
     * 
     * @param blockNum
     * @param row
     * @param col
     */
    public void boardClickedAt(int blockNum, int row, int col);

}
