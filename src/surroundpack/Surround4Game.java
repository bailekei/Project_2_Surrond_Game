package surroundpack;

public class Surround4Game {
	private Cell[][] board;
	private int player;
	private Cell playersnum;

	public Surround4Game() {
		//super();
		board = new Cell[10][10];
		this.player = 1;
	}

	public void reset() {
		for (int r = 0; r < 10; r++) {
			//goes through all columns
			for (int c = 0; c < 10; c++) {
				//set each cell to empty
				board[r][c] = null;
			}
		}
	}

	public Cell getCell(int row, int col) {
		return board[row][col];
	}

	public int getCurrentPlayer() {
		return player;
	}

	public int nextPlayer() {
//		player = player + 1;
		if( player>2)
			player --;
//		if (player == 2)
//			player = 0;
		else{player = (player + 1) % 2;}
		return player;
	}

    /*******************************************************************************************************************
     *Method determines if the row, col that was selected was an empty square
	 *
     * @param row int current row
     * @param col int current col
     *******************************************************************************************************************/


    public boolean select(int row, int col) {
		if (board[row][col] == null ) {
			Cell c = new Cell(player);
			board[row][col] = c;
			return true;
		}

		else 
			return false;
	}

    /*******************************************************************************************************************
     *Method  that selects a winner if set conditions are met
     *******************************************************************************************************************/

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
				if (row == 0 && col == 0){
					if (board[0][1] != null && board[1][0] != null)
						if (board[0][1].getPlayerNumber() == board[1][0].getPlayerNumber())
							return player;}
// left-border case (excluding corners - check 3 sides only)
				if (row != 0 && row != 10-1 && col == 0) {
					if (board[row - 1][col] != null && board[row][col + 1] != null && board[row + 1][col] != null) {
						if (board[row - 1][col].getPlayerNumber() == board[row][col + 1].getPlayerNumber() &&
								board[row - 1][col].getPlayerNumber() == board[row + 1][col].getPlayerNumber())
							return player; // just pick one of them
					}
				}
			}

		}
		return -1;
	}
}






