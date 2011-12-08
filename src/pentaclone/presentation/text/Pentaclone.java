/** Handles a text game interface.
 * 
 */
package pentaclone.presentation.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pentaclone.logic.Board;
import pentaclone.logic.IBoard;

/**
 * Text interface.
 * 
 * @author Diego
 * 
 */
public class Pentaclone {

    /**
     * Entry point for the text interface.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Pentaclone p = new Pentaclone();
        p.start();
    }

    /**
     * Start interface.
     * 
     */
    public void start() {
        boolean exit = false;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(isr);
            int choice;
            do {
                choice = showMainMenu(reader);

                switch (choice) {
                    case 1:
                        play(reader);
                        break;
                    case 2:
                        showHelp(reader);
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Thank you for playing. Bye!");
                        break;
                    default:
                        throw new Exception("Invalid return value.");
                }
            } while (!exit);
        } catch (IOException e) {
            System.out.println(e.toString());
            System.out.println();
            System.out.println("Sorry. Bye!");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println();
            System.out.println("Sorry. Bye!");
        }
    }

    private int showMainMenu(BufferedReader reader) throws IOException {
        String line;
        boolean exit = false;
        int result = -1;

        System.out.println("Welcome to pentaclone!");
        System.out.println();
        do {
            System.out.println("1. Play");
            System.out.println("2. Instructions / Help");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            line = reader.readLine();
            if (line != null && line.length() == 1 && Character.isDigit(line.charAt(0)) && Integer.parseInt(line) >= 1 && Integer.parseInt(line) <= 3) {
                result = Integer.parseInt(line);
                exit = true;
            } else {
                System.out.println("Invalid number. Try again.");
            }
        } while (!exit);

        return result;
    }

    private void play(BufferedReader reader) throws IOException {
        char player1Initial, player2Initial;
        IBoard board;
        int turn = 0;

        player1Initial = getInitial(reader, 1);
        player2Initial = getInitial(reader, 2);

        board = new Board();
        board.newGame(player1Initial, player2Initial);
        printBoard(board.getBoardAsCharArray());

        do {
            turn++;
            // Player 1
            validMove(reader, 1, player1Initial, board);
            printBoard(board.getBoardAsCharArray());
            if (board.hasWinner() == board.getPlayer1()) {
                showWinner(1, player1Initial, turn);
                return;
            }
            validRotation(reader, board);
            printBoard(board.getBoardAsCharArray());
            if (board.hasWinner() == board.getPlayer1()) {
                showWinner(1, player1Initial, turn);
                return;
            }
            // Player 2
            printBoard(board.getBoardAsCharArray());
            validMove(reader, 2, player2Initial, board);
            printBoard(board.getBoardAsCharArray());
            if (board.hasWinner() == board.getPlayer2()) {
                showWinner(2, player2Initial, turn);
                return;
            }
            validRotation(reader, board);
            printBoard(board.getBoardAsCharArray());
            if (board.hasWinner() == board.getPlayer2()) {
                showWinner(2, player2Initial, turn);
                return;
            }
        // Only exits when there is a winner or a tie game.
        } while (board.hasWinner() != IBoard.TIE);
        System.out.println("The game is tied");
        System.out.println();
    }

    private void showWinner(int playerNumber, char playerInitial, int turn) {
        System.out.println();
        System.out.println("Congratulations player " + playerNumber + " (" + playerInitial + ")!");
        System.out.println("You won in only " + turn + " turns!");
        System.out.println();
    }

    private void validMove(BufferedReader reader, int playerNumber,
            char playerInitial, IBoard board) throws IOException {
        boolean exit = false;
        System.out.println("Player " + playerNumber + " (" + playerInitial + "):");
        do {
            int row = getRow(reader);
            int col = getColumn(reader);

            if (!board.move(row, col) && board.hasWinner() != IBoard.TIE) {
                System.out.println("Invalid move. Try again.");
            } else {
                exit = true;
            }
        } while (!exit);
    }

    private void validRotation(BufferedReader reader, IBoard board)
            throws IOException {
        if (board.hasWinner() == IBoard.TIE) {
            return;
        }
        boolean canSkipRotation = false;

        if (board.isNeutral(1) || board.isNeutral(2) || board.isNeutral(3) || board.isNeutral(4)) {
            canSkipRotation = true;
        }
        char block = getBlock(reader, canSkipRotation);
        if (block != 'N') {
            char direction = getDirection(reader);
            if (direction == 'L') {
                board.rotateLeft(Character.digit(block, 10));
            } else {
                board.rotateRight(Character.digit(block, 10));
            }
        }

    }

    private char getInitial(BufferedReader reader, int playerNumber)
            throws IOException {
        String line;
        boolean exit = false;
        char result = '*';

        do {
            System.out.print("Please write player " + playerNumber + " initial: ");
            line = reader.readLine();

            if (line != null && line.length() == 1 && Character.isLetter(line.charAt(0))) {
                result = Character.toUpperCase(line.charAt(0));
                exit = true;
            } else {
                System.out.println("Invalid letter. Try again.");
            }
        } while (!exit);

        return result;
    }

    private char getBlock(BufferedReader reader, boolean canSkipRotation)
            throws IOException {
        String line;
        boolean exit = false;
        char result = '*';

        do {
            System.out.println("1|2");
            System.out.println("-+-");
            System.out.println("3|4");
            System.out.println();
            System.out.print("Which block do you want to rotate? (1,2,3,4" + (canSkipRotation ? ",n): " : "): "));

            line = reader.readLine();
            if (line != null && line.length() == 1 && ((canSkipRotation && line.toUpperCase().charAt(0) == 'N') || (Character.isDigit(line.charAt(0)) && Integer.parseInt(line) >= 1 && Integer.parseInt(line) <= 4))) {
                result = line.toUpperCase().charAt(0);
                exit = true;
            } else {
                System.out.println("Invalid block. Try again.");
            }
        } while (!exit);

        return result;
    }

    private char getDirection(BufferedReader reader) throws IOException {
        String line;
        boolean exit = false;
        char result = '*';

        do {
            System.out.print("Which way do you want to rotate? (l,r)");
            line = reader.readLine();

            if (line != null && line.length() == 1 && (line.toUpperCase().charAt(0) == 'L' || line.toUpperCase().charAt(0) == 'R')) {
                result = line.toUpperCase().charAt(0);
                exit = true;
            } else {
                System.out.println("Invalid direction. Try again.");
            }

        } while (!exit);

        return result;
    }

    private int getRow(BufferedReader reader) throws IOException {
        String line;
        boolean exit = false;
        int result = -1;

        do {
            System.out.print("Row (0-5): ");

            line = reader.readLine();
            if (line != null && line.length() == 1 && Character.isDigit(line.charAt(0)) && Integer.parseInt(line) >= 0 && Integer.parseInt(line) <= 5) {
                result = Integer.parseInt(line);
                exit = true;
            } else {
                System.out.println("Invalid row. Try again.");
            }
        } while (!exit);

        return result;
    }

    private int getColumn(BufferedReader reader) throws IOException {
        String line;
        boolean exit = false;
        int result = -1;

        do {
            System.out.print("Column (0-5): ");

            line = reader.readLine();
            if (line != null && line.length() == 1 && Character.isDigit(line.charAt(0)) && Integer.parseInt(line) >= 0 && Integer.parseInt(line) <= 5) {
                result = Integer.parseInt(line);
                exit = true;
            } else {
                System.out.println("Invalid column. Try again.");
            }
        } while (!exit);

        return result;
    }

    public static void printBoard(char[][] board) {
        System.out.println();
        System.out.println("   0 1 2   3 4 5");
        System.out.println("                ");
        for (int row = 0; row < 6; row++) {
            System.out.print(row + "  ");
            for (int col = 0; col < 6; col++) {
                System.out.print(board[row][col]);
                System.out.print(" ");
                if (col == 2) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (row == 2) {
                System.out.println("   - - - + - - -");
            }
        }
        System.out.println();
    }

    private void showHelp(BufferedReader reader) throws IOException {
        System.out.println("");
        System.out.println("Welcome to Pentaclone, the mind twisting game from Colombia.");
        System.out.println("(Actually it is a rip-off from the Swedish game PENTAGO - www.pentago.com)");
        System.out.println("STARTING");
        System.out.println("The game starts with an empty game board, consisting of four twistable game ");
        System.out.println("blocks. ");
        System.out.println("OBJECT");
        System.out.println("The object is to get five marbles in a row before your opponent does. The mind");
        System.out.println("twisting part of Pentago is that each player will also twist one of the four");
        System.out.println("game blocks 90 degrees (one �notch�), clockwise or counter clockwise, as part");
        System.out.println("of each turn. A 180 degree (double �notch�) twist is not allowed. The twist is");
        System.out.println("the key to create winning positions in Pentago.");
        System.out.println("PLAYING");
        System.out.println("Players take turns at placing marbles on the game board and twisting the game ");
        System.out.println("blocks. A player is free to twist any of the game blocks, regardless of which ");
        System.out.println("game block the player placed the marble on. In the beginning of the game, ");
        System.out.println("there will be neutral game blocks, allowing a player to ignore the twist ");
        System.out.println("portion of their turn. A neutral game block is one that is empty or has only ");
        System.out.println("one marble in the middle of it. Twisting a neutral game block will have no ");
        System.out.println("effect on the positional nature of the game board so the twisting part of a ");
        System.out.println("move is optional while there are neutral game blocks.");
        System.out.print("Press ENTER to continue...");
        reader.readLine();
        System.out.println("WINNING");
        System.out.println("A winning row of five marbles can occur vertically, horizontally or ");
        System.out.println("diagonally, anywhere on the board and will span two or three game blocks. ");
        System.out.println("What seems like a simple five-in-a-row game quickly gets mind twisting as the ");
        System.out.println("board fills up and both players are twisting the game blocks, creating a ");
        System.out.println("constantly changing and challenging game scenario. You�ll want to really ");
        System.out.println("watch your opponents position as it relates to yours and play as much ");
        System.out.println("defensive as you do offense.");
        System.out.println("TIES");
        System.out.println("A tie can occur if both players have played all marbles and neither player has");
        System.out.println("five-in-a-row. A tie can also occur if one player twists his/her marbles into ");
        System.out.println("a row of five, but also creates a row of five for the opponent. However, ");
        System.out.println("should one player be able to win by simply placing a marble on the board for ");
        System.out.println("a row of five, without twisting a game block, then that game is over, without ");
        System.out.println("that player having to twist a game block, even if doing so would have resulted");
        System.out.println("in the opponent also getting five-in-a-row.  ");
        System.out.print("Press ENTER to continue...");
        reader.readLine();
        System.out.println("TWISTING THE GAME BLOCKS ");
        System.out.println("A player can choose to ignore twisting a game block in the first few moves of ");
        System.out.println("the game, while there are neutral game blocks. A neutral game block is one ");
        System.out.println("that is empty or has only one marble in the middle of it.");
        System.out.println("Twisting a neutral game block will have no effect on the positional nature of ");
        System.out.println("the game board so the twisting part of a move is optional while there are ");
        System.out.println("neutral game blocks.");
        System.out.println("This does not mean that the player cannot twist a game block in the beginning ");
        System.out.println("of the game, it is simply optional until such time that all game blocks have ");
        System.out.println("at least one marble on it that isn�t in the middle, then you have to twist a ");
        System.out.println("game block as part of your turn.	");
        System.out.print("Press ENTER to continue...");
        String line = reader.readLine();
        if (line.compareTo("Morocha") == 0) {
            System.out.println("This game is dedicated to and inspired by my girlfriend Marcela. She is ");
            System.out.println("the best thing that has ever happened to me.");
        }
        System.out.println();
        System.out.println();
    }
}
