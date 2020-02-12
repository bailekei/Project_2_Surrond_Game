package surroundpack;

public class Surround4Game {
	private Cell[][] board;
	private int player;
	private Cell playersnum;

	/**
	 * this method creates the default board size of 10 cells by 10 cells and creates the first player
	 */
	public Surround4Game() {
		//super();
		board = new Cell[10][10];
		this.player = 1;
	}

	/**
	 * the method below will reset the board so the players can continue to play without the score to reset
	 */
	public void reset() {
		for (int r = 0; r < 10; r++) {
			//goes through all columns
			for (int c = 0; c < 10; c++) {
				//set each cell to empty
				board[r][c] = null;
			}
		}
	}

	/**
	 * this method will select the square in the certain row and the column that it is in
	 * this
	 * @param row
	 * @param col
	 * @return the row and cell that the button was pushed in
	 */
	public Cell getCell(int row, int col) {
		return board[row][col];
	}

	/**
	 * this method gets the current player whether is is player one or player 99
	 * @return the player that is currently selecting a square
	 */
	public int getCurrentPlayer() {
		return player;
	}

	/**
	 * this method allows the next player in line to select a tile or start to play
	 * @return the number of the next player
	 */
	public int nextPlayer() {
//		player = player + 1;
		if( player>2)
			player --;
//		if (player == 2)
//			player = 0;
		else{player = (player + 1) % 2;}
		return player;
	}

    /**
     *Method determines if the row, col that was selected was an empty square
	 *
     * @param row int current row
     * @param col int current col
     */


    public boolean select(int row, int col) {
		if (board[row][col] == null ) {
			Cell c = new Cell(player);
			board[row][col] = c;
			return true;
		}

		else 
			return false;
	}

    /**
     *Method  that selects a winner if set conditions are met
     */

	public int getWinner() {

		int inthis = 0;

		for (int row = 0; row < 10; row++) {

			for (int col = 0; col < 10; col++) {

//				if (board[row][col] != null) {
//
//					if (board[row][col] == board[row + 1][col]) {
//						inthis++;
//						System.out.println(inthis);
//						return player;
//					}
//				}
				// top-left corner case (check 2 sides only)
				if (row == 0 && col == 0) {
					if (board[0][1] != null && board[1][0] != null)
						if (board[0][1].getPlayerNumber() == board[1][0].getPlayerNumber() && board[0][1].getPlayerNumber() == board[1][1].getPlayerNumber())
							if (board[0][1].getPlayerNumber() != board[1][0].getPlayerNumber()) {
								return board[0][1].getPlayerNumber();
							}
				}
				// bottom-left corner case (check 2 sides only)
				if (row == 9 && col == 0) {
					if (board[9][1] != null && board[9][0] != null)
						if (board[9][1].getPlayerNumber() == board[8][0].getPlayerNumber() && board[9][1].getPlayerNumber() == board[8][1].getPlayerNumber())
							if (board[9][1].getPlayerNumber() != board[9][0].getPlayerNumber()) {
								return board[9][1].getPlayerNumber();
							}
				}
				// top-right corner case (check 2 sides only)
				if (row == 0 && col == 9) {
					if (board[0][9] != null && board[1][9] != null)
						if (board[0][8].getPlayerNumber() == board[1][9].getPlayerNumber() && board[1][8].getPlayerNumber() == board[0][8].getPlayerNumber())
							if (board[0][9].getPlayerNumber() != board[0][8].getPlayerNumber()) {
								return board[1][9].getPlayerNumber();
							}
				}
				// bottom-right corner case (check 2 sides only)
				if (row == 9 && col == 9) {
					if (board[8][9] != null && board[9][8] != null)
						if (board[8][9].getPlayerNumber() == board[8][8].getPlayerNumber() && board[9][8].getPlayerNumber() == board[8][8].getPlayerNumber())
							if (board[9][9].getPlayerNumber() != board[9][8].getPlayerNumber()) {
								return board[8][9].getPlayerNumber();
							}
				}
				// left-border case (excluding corners - check 3 sides only)
				if (row != 0 && row != 9 && col == 0) {
					if (board[row - 1][col] != null && board[row][col + 1] != null && board[row + 1][col] != null) {
						if (board[row - 1][col].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
								board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber())
							return board[row-1][col].getPlayerNumber(); // just pick one of them
					}
				}
				// right-border case (excluding corners - check 3 sides only)
				if (row != 0 && row != 9 && col == 9) {
					if (board[row - 1][col] != null && board[row][col - 1] != null && board[row + 1][col] != null) {
						if (board[row - 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
								board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber())
							return board[row+1][col].getPlayerNumber(); // just pick one of them
					}
				}
				// middle win
				if (row != 0 && row != 9 && col != 0 && col != 9) {
					if (board[row - 1][col] != null && board[row][col - 1] != null && board[row + 1][col] != null) {
						if (board[row + 1][col].getPlayerNumber() == board[row][col - 1].getPlayerNumber() &&
								board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber() && board[row][col + 1].getPlayerNumber() == board[row + 1][col].getPlayerNumber())
							if (board[row][col].getPlayerNumber() != board[row+1][col].getPlayerNumber()) {
								return board[row + 1][col].getPlayerNumber(); // just pick one of them
							}
					}
				}

				//side win return to later
				if (board[row][col] != null && col != 0 && col != 9) {
					if (board[row][col-1] != null && board[row][col + 1] != null) {
						if (board[row][col-1].getPlayerNumber() == board[row][col + 1].getPlayerNumber())
							if(board[row][col-1].getPlayerNumber() != board[row][col].getPlayerNumber())
							return board[row][col+1].getPlayerNumber(); // just pick one of them
					}
				}
			}

		}
		return -1;
	}
}






