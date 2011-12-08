/**
 * 
 */
package pentaclone.presentation.swing;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import pentaclone.logic.IBoard;

/**
 * @author Diego
 * 
 */
public class BoardPanel extends JPanel implements IBlockReporter {

	private static final long serialVersionUID = 1L;

	private BlockPanel blockPanel1 = null;

	private BlockPanel blockPanel2 = null;

	private BlockPanel blockPanel3 = null;

	private BlockPanel blockPanel4 = null;

	/**
     * This is the default constructor
     */
	public BoardPanel() {
		super();
		initialize();
	}

	/**
     * This method initializes this
     * 
     * @return void
     */
	private void initialize() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(2);
		gridLayout.setHgap(3);
		gridLayout.setVgap(3);
		gridLayout.setColumns(2);
		this.setLayout(gridLayout);
		this.setSize(607, 607);
		this.setBackground(Color.orange);
		this.add(getBlockPanel1(), null);
		this.add(getBlockPanel2(), null);
		this.add(getBlockPanel3(), null);
		this.add(getBlockPanel4(), null);

	}

	/**
     * This method initializes blockPanel1
     * 
     * @return pentaclone.presentation.swing.BlockPanel
     */
	private BlockPanel getBlockPanel1() {
		if (blockPanel1 == null) {
			blockPanel1 = new BlockPanel();
		}
		return blockPanel1;
	}

	/**
     * This method initializes blockPanel2
     * 
     * @return pentaclone.presentation.swing.BlockPanel
     */
	private BlockPanel getBlockPanel2() {
		if (blockPanel2 == null) {
			blockPanel2 = new BlockPanel();
		}
		return blockPanel2;
	}

	/**
     * This method initializes blockPanel3
     * 
     * @return pentaclone.presentation.swing.BlockPanel
     */
	private BlockPanel getBlockPanel3() {
		if (blockPanel3 == null) {
			blockPanel3 = new BlockPanel();
		}
		return blockPanel3;
	}

	/**
     * This method initializes blockPanel4
     * 
     * @return pentaclone.presentation.swing.BlockPanel
     */
	private BlockPanel getBlockPanel4() {
		if (blockPanel4 == null) {
			blockPanel4 = new BlockPanel();
		}
		return blockPanel4;
	}

	// ------ additional model fields -----------
	private IBoard model; // @jve:decl-index=0:

	private IBoardReporter reporter;

	/**
     * Hooks up the game model to the interface. Makes additional references
     * grid locations buttons in the interface so that they can be referred to
     * using an array.
     */
	public void modelHookupInitialize(IBoard theModel) {
		model = theModel;

		getBlockPanel1().setBlockNum(1);
		getBlockPanel2().setBlockNum(2);
		getBlockPanel3().setBlockNum(3);
		getBlockPanel4().setBlockNum(4);
		getBlockPanel1().setReporter(this);
		getBlockPanel2().setReporter(this);
		getBlockPanel3().setReporter(this);
		getBlockPanel4().setReporter(this);
		getBlockPanel1().modelHookupInitialize(model);
		getBlockPanel2().modelHookupInitialize(model);
		getBlockPanel3().modelHookupInitialize(model);
		getBlockPanel4().modelHookupInitialize(model);
	}

	/**
     * Refreshes the view of the model by asking the model for the latest
     * information and recreating the display. This should be called if the
     * model may have changed since its view was last displayed as may occur
     * after the model processes a user request.
     */
	public void updateGUI() {
		getBlockPanel1().updateGUI();
		getBlockPanel2().updateGUI();
		getBlockPanel3().updateGUI();
		getBlockPanel4().updateGUI();
	}
	

	/**
     * Responds to user clicks on grid locations. User requests are passed to
     * the model. After the model finishes processing the requests the view is
     * updated.
     * 
     */
	public void blockClickedAt(int blockNum, int row, int col) {
		int rowOffset = 0, colOffset = 0;
		switch (blockNum) {
		case 1:
			rowOffset = 0;
			colOffset = 0;
			break;
		case 2:
			rowOffset = 0;
			colOffset = 3;
			break;
		case 3:
			rowOffset = 3;
			colOffset = 0;
			break;
		case 4:
			rowOffset = 3;
			colOffset = 3;
			break;
		default:
			System.out.println("ERROR: Unexpected block number.");
			break;
		}
		reporter.boardClickedAt(blockNum, row + rowOffset, col + colOffset);
	}

	/**
     * @param reporter
     *            the reporter to set
     */
	public void setReporter(IBoardReporter reporter) {
		this.reporter = reporter;
	}

}
