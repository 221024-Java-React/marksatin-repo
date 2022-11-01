import java.util.Scanner;

// Tic Tac Toe Game
public class Lab5 {
	char board[][] = new char[3][3];
	char currentPlayer = 'X';
	int row = 0;
	int col = 0;
	
	void validateMove(int row, int col, char[][] board) {
		char index = board[row-1][col-1];
		System.out.println("index: " + index);
		
		if (index == '_') {
			index = currentPlayer;
		} else {
			System.out.println("That space is filled. Please choose another space.");
		}
		
		System.out.println("index: " + index);
	}
	
	void changePlayer() {
		if (currentPlayer == 'X') {
			currentPlayer = 'O';
		} else {
			currentPlayer = 'X';
		}
	}
	
	boolean checkForWinner() {
		return false;
	}
	
	public static void main(String[] args) {
		Lab5 l5 = new Lab5();
		Scanner input = new Scanner(System.in);
		boolean winner = false;
		
		// Initialize board with underscores
		for (int i = 0; i < 3; i++ ) {
			for (int j = 0; j < 3; j++) {
				l5.board[i][j] = '_';
			}
		}
		
		// While no one has won the game...
//		while (!winner) {
			// Get Player 1's piece index
			System.out.println("Player 1 begin. Where would you like to place your piece?");
			
			do {
				System.out.println("Please enter a row number between 1 and 3.");
				l5.row = input.nextInt();
				System.out.println("Please enter a column number between 1 and 3.");
				l5.col = input.nextInt();
				System.out.println(l5.row);
				System.out.println(l5.col);
			} while (l5.row < 1 || l5.row > 3 || l5.col < 1 || l5.col > 3);
			
			input.close();
//		}

		l5.validateMove(l5.row, l5.col, l5.board);
		}
}
