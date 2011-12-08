/**
 * 
 */
package pentaclone.presentation.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import pentaclone.logic.IBoard;

/**
 * @author Diego
 * 
 */
public class BlockPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JButton jButton1 = null;
    private JButton jButton2 = null;
    private JButton jButton3 = null;
    private JButton jButton4 = null;
    private JButton jButton5 = null;
    private JButton jButton6 = null;
    private JButton jButton7 = null;
    private JButton jButton8 = null;
    private JButton jButton9 = null;

    /**
     * This is the default constructor
     */
    public BlockPanel() {
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
        gridLayout.setRows(3);
        gridLayout.setHgap(1);
        gridLayout.setVgap(1);
        gridLayout.setColumns(3);
        this.setLayout(gridLayout);
        this.setSize(302, 302);
        this.setBackground(Color.yellow);
        this.add(getJButton1(), null);
        this.add(getJButton2(), null);
        this.add(getJButton3(), null);
        this.add(getJButton4(), null);
        this.add(getJButton5(), null);
        this.add(getJButton6(), null);
        this.add(getJButton7(), null);
        this.add(getJButton8(), null);
        this.add(getJButton9(), null);
    }

    /**
     * This method initializes jButton1
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButton1() {
        if (jButton1 == null) {
            jButton1 = new JButton();
            jButton1.setPreferredSize(new Dimension(100, 100));
        }
        return jButton1;
    }

    /**
     * This method initializes jButton2
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButton2() {
        if (jButton2 == null) {
            jButton2 = new JButton();
            jButton2.setPreferredSize(new Dimension(100, 100));
        }
        return jButton2;
    }

    /**
     * This method initializes jButton3
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButton3() {
        if (jButton3 == null) {
            jButton3 = new JButton();
            jButton3.setPreferredSize(new Dimension(100, 100));
        }
        return jButton3;
    }

    /**
     * This method initializes jButton4
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButton4() {
        if (jButton4 == null) {
            jButton4 = new JButton();
            jButton4.setPreferredSize(new Dimension(100, 100));
        }
        return jButton4;
    }

    /**
     * This method initializes jButton5
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButton5() {
        if (jButton5 == null) {
            jButton5 = new JButton();
            jButton5.setPreferredSize(new Dimension(100, 100));
        }
        return jButton5;
    }

    /**
     * This method initializes jButton6
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButton6() {
        if (jButton6 == null) {
            jButton6 = new JButton();
            jButton6.setPreferredSize(new Dimension(100, 100));
        }
        return jButton6;
    }

    /**
     * This method initializes jButton7
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButton7() {
        if (jButton7 == null) {
            jButton7 = new JButton();
            jButton7.setPreferredSize(new Dimension(100, 100));
        }
        return jButton7;
    }

    /**
     * This method initializes jButton8
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButton8() {
        if (jButton8 == null) {
            jButton8 = new JButton();
            jButton8.setPreferredSize(new Dimension(100, 100));
        }
        return jButton8;
    }

    /**
     * This method initializes jButton9
     * 
     * @return javax.swing.JButton
     */
    private JButton getJButton9() {
        if (jButton9 == null) {
            jButton9 = new JButton();
            jButton9.setPreferredSize(new Dimension(100, 100));
        }
        return jButton9;
    }
    /**
     * The block number
     */
    private int blockNum;

    /**
     * @param blockNum
     *            the blockNum to set
     */
    public void setBlockNum(int blockNum) {
        this.blockNum = blockNum;
    }

    // Model
    private IBoard model;
    private IBlockReporter reporter; // @jve:decl-index=0:
    private JButton boardPosition[][];

    // images to use as board locations
    private ImageIcon xIcon;
    private ImageIcon xWinIcon;
    private ImageIcon oIcon;
    private ImageIcon oWinIcon;
    private ImageIcon emptyIcon;

    /**
     * Hooks up the game model to the interface. Makes additional references
     * grid locations buttons in the interface so that they can be referred to
     * using an array.
     */
    public void modelHookupInitialize(IBoard theModel) {
        model = theModel;

        xIcon = createImgIcon("images/x.jpg");
        oIcon = createImgIcon("images/o.jpg");
        xWinIcon = createImgIcon("images/xwin.jpg");
        oWinIcon = createImgIcon("images/owin.jpg");
        emptyIcon = createImgIcon("images/empty.jpg");

        getJButton1().addActionListener(this);
        getJButton2().addActionListener(this);
        getJButton3().addActionListener(this);
        getJButton4().addActionListener(this);
        getJButton5().addActionListener(this);
        getJButton6().addActionListener(this);
        getJButton7().addActionListener(this);
        getJButton8().addActionListener(this);
        getJButton9().addActionListener(this);
        getJButton1().setName("0,0");
        getJButton2().setName("0,1");
        getJButton3().setName("0,2");
        getJButton4().setName("1,0");
        getJButton5().setName("1,1");
        getJButton6().setName("1,2");
        getJButton7().setName("2,0");
        getJButton8().setName("2,1");
        getJButton9().setName("2,2");
        boardPosition = new JButton[3][3];
        boardPosition[0][0] = getJButton1();
        boardPosition[0][1] = getJButton2();
        boardPosition[0][2] = getJButton3();
        boardPosition[1][0] = getJButton4();
        boardPosition[1][1] = getJButton5();
        boardPosition[1][2] = getJButton6();
        boardPosition[2][0] = getJButton7();
        boardPosition[2][1] = getJButton8();
        boardPosition[2][2] = getJButton9();
    }

    /**
     * Refreshes the view of the model by asking the model for the latest
     * information and recreating the display. This should be called if the
     * model may have changed since its view was last displayed as may occur
     * after the model processes a user request.
     */
    public void updateGUI() {
        char[][] theBoardConfig;
        theBoardConfig = model.getBoardAsCharArray(); // find out what's
        // changed
        char winCode;
        winCode = model.hasWinner();
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
        char player1, player2;
        player1 = model.getPlayer1();
        player2 = model.getPlayer2();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (theBoardConfig[i + rowOffset][j + colOffset] == player1) {
                    if (winCode == player1 && theBoardConfig[i + rowOffset][j + colOffset] == winCode) {
                        boardPosition[i][j].setIcon(xWinIcon);
                    } else {
                        boardPosition[i][j].setIcon(xIcon);
                    }
                }

                if (theBoardConfig[i + rowOffset][j + colOffset] == player2) {

                    if (winCode == player2 && theBoardConfig[i + rowOffset][j + colOffset] == winCode) {
                        boardPosition[i][j].setIcon(oWinIcon);
                    } else {
                        boardPosition[i][j].setIcon(oIcon);
                    }
                }
                if (theBoardConfig[i + rowOffset][j + colOffset] == IBoard.EMPTY) {
                    boardPosition[i][j].setIcon(emptyIcon);
                }

            }
        }
    }

    // Method to load ImageIcons from a file - if the image cannot
    // be loaded correctly it creates an ImageIcon with an error
    // message inside it
    ImageIcon createImgIcon(String imgName) {
        ImageIcon imIc = new ImageIcon();
        final int IMG_WID = 100; // set to match desired
        final int IMG_HT = 100; // image width and height
        int loadStat = MediaTracker.ERRORED;
        System.out.println(System.getProperty("user.dir"));

        ClassLoader loader = getClass().getClassLoader();
        URL theURL = getClass().getResource(imgName);
        boolean badURL = false;
        if (theURL == null) {
            badURL = true;
        }
        if (!badURL) {
            imIc = new ImageIcon(theURL);
            loadStat = imIc.getImageLoadStatus();
        }
        if (badURL || (loadStat != MediaTracker.COMPLETE)) {
            // Create an error icon
            BufferedImage bufImg = new BufferedImage(IMG_WID, IMG_HT,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, IMG_WID, IMG_HT);
            g.setColor(Color.RED);
            g.drawString("Image File", 0, 20);
            g.setColor(Color.BLACK);
            g.drawString(imgName, 0, 50);
            g.setColor(Color.RED);
            g.drawString("NOT FOUND", 0, 80);
            imIc = new ImageIcon(bufImg);
        }
        return imIc;
    }

    /**
     * @param reporter
     *            the reporter to set
     */
    public void setReporter(IBlockReporter reporter) {
        this.reporter = reporter;
    }

    /**
     * Handles the "click on button" event
     */
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        int row, col;
        row = Integer.parseInt(sourceButton.getName().substring(0, 1));
        col = Integer.parseInt(sourceButton.getName().substring(2, 3));
        reporter.blockClickedAt(blockNum, row, col);
        sourceButton.getName();
    }
}
