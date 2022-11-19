package labs_1_5;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Lab5 {
	char[][] board = new char[3][3];
	char currentPlayer = 'X';
	final int GRID_LENGTH = 3;
	final int MAX_TURNS = 9;
	
	public char[][] initBoard() {
		
		for (char[] row : board) {
			Arrays.fill(row, '_');
		}
		
		return board;
	}
	
	public int[] getIndex(Scanner scanner) {
		
		int[] index = new int[2];
		
		try {
			System.out.println("Enter row position (int): ");
			int row = scanner.nextInt();
			index[0] = row;
			
			System.out.println("Enter column position (int): ");
			int col = scanner.nextInt();			
			index[1] = col;
			
		} catch (InputMismatchException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		
		return index;
	}
	
	public boolean winnerExists() {
		
		StringBuilder sb = new StringBuilder();
		
		// CHECK ROWS
		for (int i = 0; i < GRID_LENGTH; i++) {
			for (int j = 0; j < GRID_LENGTH; j++) {
				sb.append(board[i][j]);
			}
			
			if (sb.toString().equals("XXX") || sb.toString().equals("OOO")) {
				return true;
			}
			
			sb.setLength(0);
		}
		
		// CHECK COLUMNS
		for (int i = 0; i < GRID_LENGTH; i++) {
			for (int j = 0; j < GRID_LENGTH; j++) {
				sb.append(board[j][i]);				
			}
			
			if (sb.toString().equals("XXX") || sb.toString().equals("OOO")) {
				return true;
			}
			
			sb.setLength(0);
		}
		
		// CHECK FORWARD DIAGONAL
		for (int i = 0; i < GRID_LENGTH; i++) {
			sb.append(board[i][i]);	
		}
			
		if (sb.toString().equals("XXX") || sb.toString().equals("OOO")) {
			return true;
		}
		
		sb.setLength(0);		
		
		// CHECK BACKWARD DIAGONAL
		for (int i = 0; i < GRID_LENGTH; i++) {
			sb.append(board[i][GRID_LENGTH - i - 1]);
		}

		if (sb.toString().equals("XXX") || sb.toString().equals("OOO")) {
			return true;
		}
		
		sb.setLength(0);

		// NO WINNER YET
		return false;
	}
	
	public char changePlayer(char currentPlayer) {
		
		char newPlayer;
		
		if (currentPlayer == 'X') {
			newPlayer = 'O';			
		} else {
			newPlayer = 'X';
		}
		
		return newPlayer;
	}
	
	public void printBoard() {
		
		for (char[] row : board) {
			for (char pos : row) {
				System.out.print(pos);
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		
		Lab5 l5 = new Lab5();
		Scanner scanner = new Scanner(System.in);
		int turns = 0;
		
		l5.initBoard();
		
		while (turns < l5.MAX_TURNS) {
			
			while (true) {
				// Get current player's play (index)
				int[] index = l5.getIndex(scanner);
				
				// Check which piece is currently at that index
				char boardPos = l5.board[index[0]][index[1]];
			
				// If space is available to play, then add current player's piece
				if (boardPos == '_') {
					l5.board[index[0]][index[1]] = l5.currentPlayer;
					break;
					
				// otherwise respond and restart the loop
				} else {
					System.out.println("There is already an " + boardPos + " at this space. Please choose another index.");				
				}	
			}
			
			if (!l5.winnerExists()) {
				l5.currentPlayer = l5.changePlayer(l5.currentPlayer);
				
			} else {
				scanner.close();
				l5.printBoard();
				System.out.println("");
				System.out.println("PLAYER " + l5.currentPlayer + " WON THE GAME!");
				return;
			}
			
			l5.printBoard();
			turns++;
		}
		
		scanner.close();
		System.out.println("Tie!");
	}
}

