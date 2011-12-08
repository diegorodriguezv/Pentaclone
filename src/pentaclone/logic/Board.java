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
public class Board implements IBoard {

	private char[][] board;

	private char player1, player2;

	private char playerToMove;
	
	private int moves;

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
	public void newGame(char player1, char player2) {
		this.board = new char[6][6];
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 6; col++) {
				this.board[row][col] = EMPTY;
			}
		}
		this.player1 = player1;
		this.player2 = player2;
		playerToMove = player1;
		moves = 0;
	}

	/**
     * Returns the board represented as a char[6][6]. The first index is the row
     * and the second is the column. A space (' ') represents an empty space. A
     * player initial represents that player's marble.
     * 
     * @return board represented as a char[6][6]
     */
	public char[][] getBoardAsCharArray() {
		return (char[][]) this.board.clone();
	}

	/**
     * Puts a marble in an empty place.
     * 
     * @param row
     * @param column
     * 
     * @return <code>false</code> if the move is invalid. <code>true</code>
     *         if the play could be done.
     */
	public boolean move(int row, int column) {
		if (board[row][column] != EMPTY || hasWinner() != NOWINNERYET) {
			return false;
		}
		board[row][column] = playerToMove;
		if (playerToMove == player1) {
			playerToMove = player2;
		} else {
			playerToMove = player1;
		}
		moves++;
		return true;
	}

	/**
     * Rotates a given block (1-4) to the left.
     * 
     * @param block
     *            A block number from 1 to 4
     */
	public void rotateLeft(int block) {
		// Find the center of the block to be rotated
		int centerRow, centerCol;
		switch (block) {
		case 1:
			centerRow = 1;
			centerCol = 1;
			break;
		case 2:
			centerRow = 1;
			centerCol = 4;
			break;
		case 3:
			centerRow = 4;
			centerCol = 1;
			break;
		case 4:
			centerRow = 4;
			centerCol = 4;
			break;
		default:
			return;
		}

		// temp = N
		char temp = board[centerRow][centerCol - 1];
		// N = W
		board[centerRow][centerCol - 1] = board[centerRow - 1][centerCol];
		// W = S
		board[centerRow - 1][centerCol] = board[centerRow][centerCol + 1];
		// S = E
		board[centerRow][centerCol + 1] = board[centerRow + 1][centerCol];
		// E = N
		board[centerRow + 1][centerCol] = temp;
		// temp = NW
		temp = board[centerRow - 1][centerCol - 1];
		// NW = SW
		board[centerRow - 1][centerCol - 1] = board[centerRow - 1][centerCol + 1];
		// SW = SE
		board[centerRow - 1][centerCol + 1] = board[centerRow + 1][centerCol + 1];
		// SE = NE
		board[centerRow + 1][centerCol + 1] = board[centerRow + 1][centerCol - 1];
		// NE = NW
		board[centerRow + 1][centerCol - 1] = temp;

	}

	/**
     * Returns true if the given block is neutral
     * 
     * @param block
     *            A block number from 1 to 4
     * @return <code>true</code> if the given block is neutral
     */
	public boolean isNeutral(int block) {
		// Find the center of the block to be rotated
		int centerRow, centerCol;
		switch (block) {
		case 1:
			centerRow = 1;
			centerCol = 1;
			break;
		case 2:
			centerRow = 1;
			centerCol = 4;
			break;
		case 3:
			centerRow = 4;
			centerCol = 1;
			break;
		case 4:
			centerRow = 4;
			centerCol = 4;
			break;
		default:
			return false;
		}
		return
		// N
		board[centerRow][centerCol - 1] == EMPTY &&
		// NE
				board[centerRow + 1][centerCol - 1] == EMPTY &&
				// E
				board[centerRow + 1][centerCol] == EMPTY &&
				// SE
				board[centerRow + 1][centerCol + 1] == EMPTY &&
				// S
				board[centerRow][centerCol + 1] == EMPTY &&
				// SW
				board[centerRow - 1][centerCol + 1] == EMPTY &&
				// W
				board[centerRow - 1][centerCol] == EMPTY &&
				// NW
				board[centerRow - 1][centerCol - 1] == EMPTY;
	}

	/**
     * Rotates a given block (1-4) to the right.
     * 
     * @param block
     *            A block number from 1 to 4
     */
	public void rotateRight(int block) {
		// Find the center of the block to be rotated
		int centerRow, centerCol;
		switch (block) {
		case 1:
			centerRow = 1;
			centerCol = 1;
			break;
		case 2:
			centerRow = 1;
			centerCol = 4;
			break;
		case 3:
			centerRow = 4;
			centerCol = 1;
			break;
		case 4:
			centerRow = 4;
			centerCol = 4;
			break;
		default:
			return;
		}

		// temp = N
		char temp = board[centerRow][centerCol - 1];
		// N = E
		board[centerRow][centerCol - 1] = board[centerRow + 1][centerCol];
		// E = S
		board[centerRow + 1][centerCol] = board[centerRow][centerCol + 1];
		// S = W
		board[centerRow][centerCol + 1] = board[centerRow - 1][centerCol];
		// W = N
		board[centerRow - 1][centerCol] = temp;
		// temp = SW
		temp = board[centerRow - 1][centerCol + 1];
		// SW = NW
		board[centerRow - 1][centerCol + 1] = board[centerRow - 1][centerCol - 1];
		// NW = NE
		board[centerRow - 1][centerCol - 1] = board[centerRow + 1][centerCol - 1];
		// NE = SE
		board[centerRow + 1][centerCol - 1] = board[centerRow + 1][centerCol + 1];
		// SE = SW
		board[centerRow + 1][centerCol + 1] = temp;

	}

	/**
     * Returns the initial of the winner of the game if there is one. If there
     * is no winner yet it returns NOWINNERYET. If the game is tied it returns
     * TIE.
     * 
     * @return The initial of the winner of the game or NOWINNERYET or TIE 
     */
	public char hasWinner() {
		if (moves == 36){
			return TIE;
		}
		if (checkCols(this.player1) || checkRows(this.player1)
				|| checkDiagonals(this.player1)) {
			return this.player1;
		}
		if (checkCols(this.player2) || checkRows(this.player2)
				|| checkDiagonals(this.player2)) {
			return this.player2;
		}
		return NOWINNERYET;
	}

	/**
     * Returns true if there are 5 horizontal adjacent marbles for the given
     * player
     * 
     * @param player
     *            initial
     * @return <code>true</code> if player has 5 horizontal adjacent marbles
     *         player
     */
	private boolean checkRows(char player) {
		for (int row = 0; row < 6; row++) {
			int count = 0, maxCount = 0;
			for (int col = 0; col < 6; col++) {
				if (board[row][col] == player) {
					count++;
					maxCount = count;
				} else {
					count = 0;
				}
			}
			if (maxCount >= 5) {
				return true;
			}
		}
		return false;
	}

	/**
     * Returns true if there are 5 vertical adjacent marbles for the given
     * player
     * 
     * @param player
     *            initial
     * @return <code>true</code> if player has 5 vertical adjacent marbles
     */
	private boolean checkCols(char player) {
		for (int col = 0; col < 6; col++) {
			int count = 0, maxCount = 0;
			for (int row = 0; row < 6; row++) {
				if (board[row][col] == player) {
					count++;
					maxCount = count;
				} else {
					count = 0;
				}
			}
			if (maxCount >= 5) {
				return true;
			}
		}
		return false;
	}

	/**
     * Returns true if there are 5 diagonal adjacent marbles for the given
     * player
     * 
     * @param player
     *            initial
     * @return <code>true</code> if player has 5 vertical adjacent marbles
     */
	private boolean checkDiagonals(char player) {
		// Checking descending diagonals
		// There are 3 diagonals that can hold a line of 5.
		for (int colOffset = -1; colOffset <= 1; colOffset++) {
			int count = 0, maxCount = 0;
			for (int row = 0; row < 6; row++) {
				if ((colOffset + row) >= 0 && (colOffset + row) < 6) {
					if (board[row][colOffset + row] == player) {
						count++;
						maxCount = count;
					} else {
						count = 0;
					}
				}
			}
			if (maxCount >= 5) {
				return true;
			}
		}
		// Checking ascending diagonals
		// There are 3 diagonals that can hold a line of 5.
		for (int colOffset = -1; colOffset <= 1; colOffset++) {
			int count = 0, maxCount = 0;
			for (int row = 0; row < 6; row++) {
				if ((colOffset + 5 - row) >= 0 && (colOffset + 5 - row) < 6) {
					if (board[row][colOffset + 5 - row] == player) {
						count++;
						maxCount = count;
					} else {
						count = 0;
					}
				}

			}
			if (maxCount >= 5) {
				return true;
			}
		}
		return false;
	}

	/**
     * Returns the initial of player 1
     * 
     * @return the initial of player 1
     */
	public char getPlayer1() {
		return player1;
	}

	/**
     * Returns the initial of player 2
     * 
     * @return the initial of player 2
     */
	public char getPlayer2() {
		return player2;
	}

	/**
     * Return the playerToMove
     * 
     * @return the playerToMove
     */
	public char getPlayerToMove() {
		return playerToMove;
	}
}
