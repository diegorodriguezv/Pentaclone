package pentaclone.test;

import pentaclone.logic.Board;
import pentaclone.logic.IBoard;

import pentaclone.presentation.text.Pentaclone;

;;

public class Test {

	public static void main(String[] args) {
		testRotate();
	}

	/**
     * 
     */
	private static void testRotate() {
		IBoard board = new Board();
		board.newGame('D', 'M');
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.move(3, 3);
		board.move(3, 4);
		// board.move('M', 0, 3);
		// board.move('M', 3, 4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
		board.rotateRight(4);
		Pentaclone.printBoard(board.getBoardAsCharArray());
	}

}
