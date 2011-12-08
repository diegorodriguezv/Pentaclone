/**
 * 
 */
package pentaclone.presentation.swing;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import pentaclone.logic.Board;
import pentaclone.logic.IBoard;

/**
 * @author Diego
 * 
 */
public class PentacloneGUI extends JFrame implements IBoardReporter,
        IRotationReporter {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private BoardPanel boardPanel = null;
    private JPanel bottomPanel = null;
    private JLabel statusMessageLabel = null;
    private JButton newGameButton = null;
    private JMenuBar mainMenuBar = null;
    private JMenu helpMenu = null;
    private JMenuItem aboutMenuItem = null;
    private IBoard model = new Board(); // @jve:decl-index=0:
    private JDialog jDialog = null; // @jve:decl-index=0:visual-constraint="655,69"
    private JPanel dialogContentPane = null;
    private JScrollPane jScrollPane = null;
    private JTextPane jTextPane = null;
    private RotationGlassPane rotationGlassPane = null; // @jve:decl-index=0:visual-constraint="661,429"
    private boolean lastMoveIllegal;

    /**
     * This method initializes boardPanel
     * 
     * @return pentaclone.presentation.swing.BoardPanel
     */
    private BoardPanel getBoardPanel() {
        if (boardPanel == null) {
            boardPanel = new BoardPanel();
        }
        return boardPanel;
    }

    /**
     * This method initializes bottomPanel
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getBottomPanel() {
        if (bottomPanel == null) {
            statusMessageLabel = new JLabel();
            statusMessageLabel.setText("Your turn player X");
            bottomPanel = new JPanel();
            bottomPanel.setLayout(new BorderLayout());
            bottomPanel.add(statusMessageLabel, BorderLayout.WEST);
            bottomPanel.add(getNewGameButton(), BorderLayout.EAST);
        }
        return bottomPanel;
    }

    /**
     * This method initializes newGameButton
     * 
     * @return javax.swing.JButton
     */
    private JButton getNewGameButton() {
        if (newGameButton == null) {
            newGameButton = new JButton();
            newGameButton.setText("New Game");
            newGameButton.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    initializeGame();
                }
            });
        }
        return newGameButton;
    }

    /**
     * This method initializes mainMenuBar
     * 
     * @return javax.swing.JMenuBar
     */
    private JMenuBar getMainMenuBar() {
        if (mainMenuBar == null) {
            mainMenuBar = new JMenuBar();
            mainMenuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            mainMenuBar.add(getHelpMenu());
        }
        return mainMenuBar;
    }

    /**
     * This method initializes helpMenu
     * 
     * @return javax.swing.JMenu
     */
    private JMenu getHelpMenu() {
        if (helpMenu == null) {
            helpMenu = new JMenu();
            helpMenu.setText("Help");
            helpMenu.add(getAboutMenuItem());
        }
        return helpMenu;
    }

    /**
     * This method initializes aboutMenuItem
     * 
     * @return javax.swing.JMenuItem
     */
    private JMenuItem getAboutMenuItem() {
        if (aboutMenuItem == null) {
            aboutMenuItem = new JMenuItem();
            aboutMenuItem.setText("About");
            aboutMenuItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    getJDialog().setVisible(true);
                }
            });
        }
        return aboutMenuItem;
    }

    /**
     * This method initializes jDialog
     * 
     * @return javax.swing.JDialog
     */
    private JDialog getJDialog() {
        if (jDialog == null) {
            jDialog = new JDialog(this);
            jDialog.setSize(new Dimension(500, 300));
            jDialog.setTitle("About");
            jDialog.setModal(true);
            jDialog.setResizable(false);
            jDialog.setContentPane(getDialogContentPane());
        }
        Point location = this.getLocation();
        jDialog.setLocation(location.x + 60, location.y + 200);
        return jDialog;
    }

    /**
     * This method initializes dialogContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getDialogContentPane() {
        if (dialogContentPane == null) {
            dialogContentPane = new JPanel();
            dialogContentPane.setLayout(new BorderLayout());
            dialogContentPane.add(getJScrollPane(), BorderLayout.CENTER);
        }
        return dialogContentPane;
    }

    /**
     * This method initializes jScrollPane
     * 
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setViewportView(getJTextPane());
        }
        return jScrollPane;
    }

    /**
     * This method initializes jTextPane
     * 
     * @return javax.swing.JTextPane
     */
    private JTextPane getJTextPane() {
        if (jTextPane == null) {
            jTextPane = new JTextPane();
            StringBuffer text = new StringBuffer();
            text.append("Welcome to Pentaclone, the mind twisting game from Colombia." + "\n");
            text.append("(Actually it is a rip-off from the Swedish game PENTAGO - www.pentago.com)" + "\n");
            text.append("STARTING" + "\n");
            text.append("The game starts with an empty game board, consisting of four twistable game " + "\n");
            text.append("blocks. " + "\n");
            text.append("OBJECT" + "\n");
            text.append("The object is to get five marbles in a row before your opponent does. The mind" + "\n");
            text.append("twisting part of Pentago is that each player will also twist one of the four" + "\n");
            text.append("game blocks 90 degrees (one �notch�), clockwise or counter clockwise, as part" + "\n");
            text.append("of each turn. A 180 degree (double �notch�) twist is not allowed. The twist is" + "\n");
            text.append("the key to create winning positions in Pentago." + "\n");
            text.append("PLAYING" + "\n");
            text.append("Players take turns at placing marbles on the game board and twisting the game " + "\n");
            text.append("blocks. A player is free to twist any of the game blocks, regardless of which " + "\n");
            text.append("game block the player placed the marble on. In the beginning of the game, " + "\n");
            text.append("there will be neutral game blocks, allowing a player to ignore the twist " + "\n");
            text.append("portion of their turn. A neutral game block is one that is empty or has only " + "\n");
            text.append("one marble in the middle of it. Twisting a neutral game block will have no " + "\n");
            text.append("effect on the positional nature of the game board so the twisting part of a " + "\n");
            text.append("move is optional while there are neutral game blocks." + "\n");
            text.append("WINNING" + "\n");
            text.append("A winning row of five marbles can occur vertically, horizontally or " + "\n");
            text.append("diagonally, anywhere on the board and will span two or three game blocks. " + "\n");
            text.append("What seems like a simple five-in-a-row game quickly gets mind twisting as the " + "\n");
            text.append("board fills up and both players are twisting the game blocks, creating a " + "\n");
            text.append("constantly changing and challenging game scenario. You�ll want to really " + "\n");
            text.append("watch your opponents position as it relates to yours and play as much " + "\n");
            text.append("defensive as you do offense." + "\n");
            text.append("TIES" + "\n");
            text.append("A tie can occur if both players have played all marbles and neither player has" + "\n");
            text.append("five-in-a-row. A tie can also occur if one player twists his/her marbles into " + "\n");
            text.append("a row of five, but also creates a row of five for the opponent. However, " + "\n");
            text.append("should one player be able to win by simply placing a marble on the board for " + "\n");
            text.append("a row of five, without twisting a game block, then that game is over, without " + "\n");
            text.append("that player having to twist a game block, even if doing so would have resulted" + "\n");
            text.append("in the opponent also getting five-in-a-row.  " + "\n");
            text.append("TWISTING THE GAME BLOCKS " + "\n");
            text.append("A player can choose to ignore twisting a game block in the first few moves of " + "\n");
            text.append("the game, while there are neutral game blocks. A neutral game block is one " + "\n");
            text.append("that is empty or has only one marble in the middle of it." + "\n");
            text.append("Twisting a neutral game block will have no effect on the positional nature of " + "\n");
            text.append("the game board so the twisting part of a move is optional while there are " + "\n");
            text.append("neutral game blocks." + "\n");
            text.append("This does not mean that the player cannot twist a game block in the beginning " + "\n");
            text.append("of the game, it is simply optional until such time that all game blocks have " + "\n");
            text.append("at least one marble on it that isn�t in the middle, then you have to twist a " + "\n");
            text.append("game block as part of your turn.	" + "\n");
            text.append("\n");
            text.append("This game is dedicated to and inspired by my girlfriend Marcela. She is " + "\n");
            text.append("the best thing that has ever happened to me.  =)");
            jTextPane.setText(text.toString());
            jTextPane.setEditable(false);
            jTextPane.setCaretPosition(0);
        }
        return jTextPane;
    }

    /**
     * This method initializes rotationGlassPane
     * 
     * @return pentaclone.presentation.swing.RotationGlassPane
     */
    private RotationGlassPane getRotationGlassPane() {
        if (rotationGlassPane == null) {
            rotationGlassPane = new RotationGlassPane(getBoardPanel(), this);
        }
        return rotationGlassPane;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                System.out.println(System.getProperty("user.dir"));

                PentacloneGUI thisClass = new PentacloneGUI();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }

    /**
     * This is the default constructor
     */
    public PentacloneGUI() {
        super();
        initialize();
        boardPanel.setReporter(this);
        boardPanel.modelHookupInitialize(model);
        initializeGame();
    }

    private void initializeGame() {
        model.newGame('X', 'O');
        lastMoveIllegal = false;
        updateGUI();
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(613, 688);
        this.setResizable(false);
        this.setJMenuBar(getMainMenuBar());
        this.setContentPane(getJContentPane());
        this.setTitle("Pentaclone");
        this.setGlassPane(getRotationGlassPane());
        this.getJDialog().setVisible(false);
    }

    /**
     * This method initializes jContentPane
     * 
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getBoardPanel(), BorderLayout.CENTER);
            jContentPane.add(getBottomPanel(), BorderLayout.SOUTH);
        }
        return jContentPane;
    }

    public void boardClickedAt(int blockNum, int row, int col) {
        if (model.hasWinner() == IBoard.NOWINNERYET) {
            if (model.move(row, col)) {
                lastMoveIllegal = false;
                updateGUI();
                getRotation();
            } else {
                lastMoveIllegal = true;
                Toolkit.getDefaultToolkit().beep();
                updateGUI();
            }
        }
    }

    public void getRotation() {
        if (model.hasWinner() == IBoard.NOWINNERYET && !(model.isNeutral(1) && model.isNeutral(2) && model.isNeutral(3) && model.isNeutral(4))) {
            String message;
            if (model.isNeutral(1) || model.isNeutral(2) || model.isNeutral(3) || model.isNeutral(4)) {
                message = "Left click->Rotate Left, Right click->Rotate Right, Middle click->Don�t Rotate";
            } else {
                message = "Left click->Rotate Left, Right click->Rotate Right";
            }
            statusMessageLabel.setText(message);
            this.getGlassPane().setVisible(true);
        }
    }

    protected void updateGUI() {
        boardPanel.updateGUI();
        int winCode = model.hasWinner();
        String mesgString = "Unknown";
        if (winCode == IBoard.NOWINNERYET) {
            if (model.getPlayer1() == model.getPlayerToMove()) {
                mesgString = "X's turn";
            } else {
                mesgString = "O's turn";
            }
            if (lastMoveIllegal) {
                mesgString = "Still " + mesgString;
            }
        } else if (winCode == IBoard.TIE) {
            mesgString = "Tie Game";
        } else if (winCode == model.getPlayer1()) {
            mesgString = "X Wins!";
        } else if (winCode == model.getPlayer2()) {
            mesgString = "O Wins!";
        }
        statusMessageLabel.setText(mesgString);
    }

    public boolean rotate(int blockNum, char direction) {
        if (direction == IRotationReporter.LEFT) {
            model.rotateLeft(blockNum);
            updateGUI();
            return true;
        }
        if (direction == IRotationReporter.RIGHT) {
            model.rotateRight(blockNum);
            updateGUI();
            return true;
        }
        if (direction == IRotationReporter.SKIP) {
            if (model.isNeutral(1) || model.isNeutral(2) || model.isNeutral(3) || model.isNeutral(4)) {
                updateGUI();
                return true;
            }
        }
        return false;
    }
}
