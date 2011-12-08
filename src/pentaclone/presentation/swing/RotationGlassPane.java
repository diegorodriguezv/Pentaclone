/**
 * 
 */
package pentaclone.presentation.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 * @author Diego
 * 
 */
public class RotationGlassPane extends JComponent implements MouseListener {

	static final String NEWLINE = System.getProperty("line.separator");

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	/**
     * The board
     */
	BoardPanel boardPanel;

	/**
     * Board reporter
     * 
     */
	IRotationReporter reporter;

	Point left, right, up, down;

	/**
     * Default constructor
     */
	public RotationGlassPane(BoardPanel boardPanel, IRotationReporter reporter) {
		this.addMouseListener(this);
		this.boardPanel = boardPanel;
		this.reporter = reporter;		

		left =  new Point(0, 303);
		right = new Point(606, 303);
		up = new Point(303, 0);
		down = new Point(303, 606);
	}

	/*
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
	protected void paintComponent(Graphics g) {
		Point boardLeft = SwingUtilities.convertPoint(this.boardPanel, left, this); 
		Point boardRight = SwingUtilities.convertPoint(this.boardPanel, right, this);
		Point boardUp = SwingUtilities.convertPoint(this.boardPanel, up, this);
		Point boardDown = SwingUtilities.convertPoint(this.boardPanel, down, this);
		g.setColor(Color.decode("#004400"));		
		
		g.drawLine(boardLeft.x, boardLeft.y, boardRight.x, boardRight.y);
		g.drawLine(boardUp.x, boardUp.y, boardDown.x, boardDown.y);
		
		g.drawLine(boardLeft.x, boardUp.y, boardRight.x, boardUp.y);
		g.drawLine(boardLeft.x, boardDown.y, boardRight.x, boardDown.y);
		
		g.drawLine(boardLeft.x, boardUp.y, boardLeft.x, boardDown.y);
		g.drawLine(boardRight.x, boardUp.y, boardRight.x, boardDown.y);
		
		g.setFont(new Font("Monospaced",Font.BOLD | Font.ITALIC,400));
		int xOffset = 30, yOffset =20;
		g.drawString("1", boardLeft.x + xOffset, boardLeft.y - yOffset);
		g.drawString("2", boardUp.x + xOffset, boardLeft.y - yOffset);
		g.drawString("3", boardLeft.x + xOffset, boardDown.y - yOffset);
		g.drawString("4", boardDown.x + xOffset, boardDown.y - yOffset);
		
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		char direction = 0;
		if (e.getButton() == MouseEvent.BUTTON1) {
			direction = IRotationReporter.LEFT;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			direction = IRotationReporter.RIGHT;
		} else if (e.getButton() == MouseEvent.BUTTON2) {
			direction = IRotationReporter.SKIP;
		}

		int blockNum = 0;
		Point clickPoint = e.getPoint();
		Point boardPoint = SwingUtilities.convertPoint(this, clickPoint,
				this.boardPanel);
		if (boardPoint.y < 0 || boardPoint.x < 0
				|| boardPoint.y > boardPanel.getSize().height
				|| boardPoint.x > boardPanel.getSize().width) { // we're not in
                                                                // the board
			Toolkit.getDefaultToolkit().beep();
		} else { // The mouse event is probably over the board pane.
			if (boardPoint.x < up.x && boardPoint.y < left.y) {
				blockNum = 1;
			}
			if (boardPoint.x > up.x && boardPoint.y < left.y) {
				blockNum = 2;
			}
			if (boardPoint.x < up.x && boardPoint.y > left.y) {
				blockNum = 3;
			}
			if (boardPoint.x > up.x && boardPoint.y > left.y) {
				blockNum = 4;
			}
			if (reporter.rotate(blockNum, direction)) {
				this.setVisible(false);
			}
			else{
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}
}
