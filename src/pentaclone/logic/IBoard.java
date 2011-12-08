/**
 * Manages the logic of a game of Pentaclone.
 */
package pentaclone.logic;

/**
 * Represents a Pentaclone game board.
 * 
 * @author Diego
 * 
 */
public interface IBoard {

	/**
     * Represents an empty board location (neither player).
     */
	public static final char EMPTY = ' ';

	/**
     * There is no winner yet. Used by <code>hasWinner()</code>.
     */
	public static final char NOWINNERYET = '-';

	/**
     * The game is tied. Used by <code>hasWinner()</code>.
     */
	public static final char TIE = '+';

	/**
     * Creates a new game board. Each player marble will be represented by a
     * character. The board has 4 blocks they are numbered left to right and top
     * to bottom.
     * 
     * @param player1
     *            First player initial
     * @param player2
     *            Second player initial
     */
	public void newGame(char player1, char player2);

	/**
     * Returns the board represented as a char[6][6]. The first index is the row
     * and the second is the column. A space (' ') represents an empty space. A
     * player initial represents that player's marble.
     * 
     * @return board represented as a char[6][6]
     */
	public char[][] getBoardAsCharArray();

	/**
     * Puts a marble in an empty place.
     * 
     * @param row
     * @param column
     * 
     * @return <code>false</code> if the move is invalid. <code>true</code>
     *         if the play could be done.
     */
	public boolean move(int row, int column);

	/**
     * Rotates a given block (1-4) to the left.
     * 
     * @param block
     *            A block number from 1 to 4
     */
	public void rotateLeft(int block);

	/**
     * Returns true if the given block is neutral
     * 
     * @param block
     *            A block number from 1 to 4
     * @return <code>true</code> if the given block is neutral
     */
	public boolean isNeutral(int block);

	/**
     * Rotates a given block (1-4) to the right.
     * 
     * @param block
     *            A block number from 1 to 4
     */
	public void rotateRight(int block);

	/**
     * Returns the initial of the winner of the game if there is one. If there
     * is no winner yet it returns NOWINNERYET. If the game is tied it returns
     * TIE.
     * 
     * @return The initial of the winner of the game or NOWINNERYET or TIE 
     */
	public char hasWinner();

	/**
     * Returns the initial of player 1
     * 
     * @return the initial of player 1
     */
	public char getPlayer1();

	/**
     * Returns the initial of player 2
     * 
     * @return the initial of player 2
     */
	public char getPlayer2();

	/**
     * Return the playerToMove
     * 
     * @return the playerToMove
     */
	public char getPlayerToMove();

}
