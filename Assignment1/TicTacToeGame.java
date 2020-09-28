/**
 * The class <b>TicTacToeGame</b> is the
 * class that implements the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintain the current state of
 * the game as players are making moves.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class TicTacToeGame {

// FINISH THE VARIABLE DECLARATION
   /**
	* The board of the game, stored as a one dimension array.
	*/
	private CellValue board[];

   /**
	* level records the number of rounds that have been
	* played so far.
	*/
	private int level;

   /**
	* gameState records the current state of the game.
	*/
	private GameState gameState = GameState.PLAYING;


   /**
	* lines is the number of lines in the grid
	*/
	private int lines;

   /**
	* columns is the number of columns in the grid
	*/
	private int columns;


   /**
	* sizeWin is the number of cell of the same type
	* that must be aligned to win the game
	*/
	private int sizeWin;


   /**
	* default constructor, for a game of 3x3, which must
	* align 3 cells
	*/
	private int boardSize;

	public TicTacToeGame() {
		lines = 3;
		columns = 3;
		sizeWin = 3;

		board = new CellValue[lines * columns];
		boardSize = board.length;

		for (int i = 0; i < boardSize; i++) {
			board[i] = CellValue.EMPTY;
		}
	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game. 3 cells must
	* be aligned.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
  	*/
	public TicTacToeGame(int lines, int columns){
		this.lines = lines;
		this.columns = columns;
		sizeWin = 3;

		board = new CellValue[lines * columns];
		boardSize = board.length;

		for (int i = 0; i < boardSize; i++) {
			board[i] = CellValue.EMPTY;
		}
	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game, as well as
	* the number of cells that must be aligned to win.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
    * @param sizeWin
    *  the number of cells that must be aligned to win.
  	*/
	public TicTacToeGame(int lines, int columns, int sizeWin){
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = sizeWin;

		board = new CellValue[lines * columns];
		boardSize = board.length;

		for (int i = 0; i < boardSize; i++) {
			board[i] = CellValue.EMPTY;
		}
	}



   /**
	* getter for the variable lines
	* @return
	* 	the value of lines
	*/
	public int getLines(){
		return lines;
	}

   /**
	* getter for the variable columns
	* @return
	* 	the value of columns
	*/
	public int getColumns(){
		return columns;
	}

   /**
	* getter for the variable level
	* @return
	* 	the value of level
	*/
	public int getLevel(){
		return level;
	}

  	/**
	* getter for the variable sizeWin
	* @return
	* 	the value of sizeWin
	*/
	public int getSizeWin(){
		return sizeWin;
	}

   /**
	* getter for the variable gameState
	* @return
	* 	the value of gameState
	*/
	public GameState getGameState(){
		return gameState;
	}

   /**
	* returns the cellValue that is expected next,
	* in other word, which played (X or O) should
	* play next.
	* This method does not modify the state of the
	* game.
	* @return
    *  the value of the enum CellValue corresponding
    * to the next expected value.
  	*/
	private int numberOfX = 0;
	private int numberOfO = 0;

	public CellValue nextCellValue() {
		CellValue nextCellValue = CellValue.EMPTY;

		for (int i = 0; i < boardSize; i++) {
			String value = board[i].toString();
			// System.out.println(value);

			if (board[i] == CellValue.X) {
				numberOfX += 1;
			}
			else if (board[i] == CellValue.O) {
				numberOfO += 1;
			}
		}

		if (numberOfX == 0 && numberOfO == 0) {
			// Beginning of the round, which means that X starts
			nextCellValue = CellValue.X;
		}
		else if (numberOfX > numberOfO) {
			nextCellValue = CellValue.O;
		}
		else if (numberOfO == numberOfX) {
			// We put "==" here because we start with X, so the amount of O's
			// can't be bigger than the amount of X's
			nextCellValue = CellValue.X;
		}

		numberOfO = 0;
		numberOfX = 0;
		return nextCellValue;
	}

   /**
	* returns the value  of the cell at
	* index i.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
   	* @param i
    *  the index of the cell in the array board
    * @return
    *  the value at index i in the variable board.
  	*/
	public CellValue valueAt(int i) {
		return board[i];
	}

	// public static void main(String[] args) { // ONLY FOR TESTING PURPOSES, REMOVE ONCE DONE
	//
	// 	TicTacToeGame g = new TicTacToeGame(2,5);
	// 	for(int i =0 ; i < 10; i++) {
	// 			g.play(i);
	// 	}
	// 	System.out.println(g.getGameState());
	//
	// }

   /**
	* This method is called when the next move has been
	* decided by the next player. It receives the index
	* of the cell to play as parameter.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
	* If the chosen cell is not empty, an error message is
	* printed out. The behaviour is then unspecified
	* If the move is valide, the board is updated, as well
	* as the state of the game.
	* To faciliate testing, is is acceptable to keep playing
	* after a game is already won. If that is the case, the
	* a message should be printed out and the move recorded.
	* the  winner of the game is the player who won first
   	* @param i
    *  the index of the cell in the array board that has been
    * selected by the next player
  	*/
	public void play(int i) {
		boolean userPlayed = false;
		boolean check = true;
		boolean playing = true;

		int count = 1; // Useful for the while loops to check if there is a winner
		int playerChoice = i+1;
		if (playerChoice > boardSize || playerChoice < 1) {
			System.out.println("The value should be between 1 and " + boardSize);
			check = false;
		}
		if (check){
			CellValue currentCellValue = board[playerChoice-1]; //CellValue currentCellValue = board[i + 1];
			switch(currentCellValue) {
				case EMPTY:
					board[playerChoice - 1] = nextCellValue(); //board[i+1] = nextCellValue();
					level += 1;
					userPlayed = true;
					break;

				case X:
					System.out.println("This cell has already been played");
					break;

				case O:
					System.out.println("This cell has already been played");
					break;
			}
		}


		System.out.println(toString());
		setGameState(i);
	}


   /**
	* A helper method which updates the gameState variable
	* correctly after the cell at index i was just set in
	* the method play(int i)
	* The method assumes that prior to setting the cell
	* at index i, the gameState variable was correctly set.
	* it also assumes that it is only called if the game was
	* not already finished when the cell at index i was played
	* (i.e. the game was playing). Therefore, it only needs to
	* check if playing at index i has concluded the game, and if
	* set the oucome correctly
	*
   	* @param i
    *  the index of the cell in the array board that has just
    * been set
  	*/


	private void setGameState(int i){

		for (int j = 0; j < boardSize; j++) {
			boolean proceed = true;
			if (j + columns > boardSize || board[j] == CellValue.EMPTY || gameState == GameState.XWIN || gameState == GameState.OWIN || gameState == GameState.DRAW) {
				if (j+columns > boardSize){
					//System.out.println("Out of range");
				}
				else{
					//System.out.println("Empty");
				}
				proceed = false;
			}

			boolean success = false;
			int consecutive = 1; // How many consecutive Os or Xs we encountered
			//System.out.println("Checking point "+j);
			if (proceed){
				// System.out.println("Checking the " + j + " value of the table");
					//-- Algorith for veritcal checks
				for (int g = j + columns; g < boardSize; g += columns){
					// if (g + columns > boardSize) {
					// 	break;
					// }
					//System.out.println("The value is " + board[g] + " at point " + g);
					if (board[g] == board[j]){
						 consecutive+= 1;
						 if (consecutive == sizeWin){
							 success = true;
								if (board[j] == CellValue.X) {
									 gameState = GameState.XWIN;
									 // System.out.println("X WINS -- Vertical");
								}
								else if (board[j] == CellValue.O) {
									 gameState = GameState.OWIN;
									 // System.out.println("O WINS -- Vertical");
								}
								else{
									//System.out.println(board[g]);
								}
						 }
						 // System.out.println(consecutive);
					}
					else{
						//System.out.println("not the same");
						 break;
					}

					if (g + columns > boardSize) {
						break;
				}
				}
				consecutive = 1;
				//-- Algorithm for horizontal checks
				if ((j+1)%columns != 0 && success == false){ //If j is last in row or a win was established, dont check for a win
					consecutive = 1;
					for (int g = j+1; g<boardSize; g++){ //Start by checking the value right next to j
						if (board[j] == board[g]){
							consecutive+= 1;
							if (consecutive == sizeWin){
								success = true;
								 if (board[j] == CellValue.X) {
										gameState = GameState.XWIN;
										// System.out.println("X WINS -- Horizontal");
								 }
								 else if (board[j] == CellValue.O) {
										gameState = GameState.OWIN;
										// System.out.println("O WINS -- Horizontal");
								 }
								 else{
									 //System.out.println(board[g]);
								 }
							}
							//System.out.println(consecutive);

						}

						else{
							break;
						}
						if ((g+1)%columns == 0){ //If the value were checking is the last in its row we break the loop.
							//System.out.println("breaking the loop");
							break;
						}
					}
				}

				//--Algorith for forward diagonal checks
				consecutive = 1;
				if ((j+1)%columns != 0 && success == false){
					for (int g = j + columns+1; g < boardSize; g += columns+1){
						if (board[g] == board[j]){
							 consecutive+= 1;
							 if (consecutive == sizeWin){
								 success = true;
									if (board[j] == CellValue.X) {
										 gameState = GameState.XWIN;
										 // System.out.println("X WINS -- Diagonal");
									}
									else if (board[j] == CellValue.O) {
										 gameState = GameState.OWIN;
										 // System.out.println("O WINS -- Diagonal");
									}
									else{
										// System.out.println(board[g]);
									}
							 }
							 // System.out.println(consecutive);
						}
						else{
							 //System.out.println("Not equal values. Checking next:");
							 break;
						}

						if (g + 1 + columns > boardSize || (g+1)%columns==0) {
							//System.out.println("Out of row");
							break;
					}
				}
			}
				//--Algorith for backward diagonal checks
				consecutive = 1;
				if ((j-1)%columns != 0 && success == false){
					for (int g = j + columns-1; g >= 0 && g<boardSize; g += (columns-1)){

						if (board[g] == board[j]){
							 consecutive+= 1;
							 if (consecutive == sizeWin){
								 success = true;
									if (board[j] == CellValue.X) {
										 gameState = GameState.XWIN;
										 // System.out.println("X WINS -- Diagonal");
									}
									else if (board[j] == CellValue.O) {
										 gameState = GameState.OWIN;
										 // System.out.println("O WINS -- Diagonal");
									}
									else{
										// System.out.println(board[g]);
									}
							 }
							 // System.out.println(consecutive);
						}
						else{
							 break;
						}

						if (g - 1 - columns < 0 || g%columns == 0) {
							break;
						}
				}
			}
				//--Algorith for draw check
	      int drawOCount = 0;
	      int drawXCount = 0;

	      for (int g = 0; g < boardSize; g++) {
	          if (success == false) {
	              if (board[g] == CellValue.X) {
	                  drawXCount += 1;
	              }

	              if (board[g] == CellValue.O) {
	                  drawOCount += 1;
	              }

	              if ((drawXCount + drawOCount == boardSize)) {
	                  gameState = GameState.DRAW;
	              }
	          }
	      }
			}
		}
	}



   /**
	* Returns a String representation of the game matching
	* the example provided in the assignment's description
	*
   	* @return
    *  String representation of the game
  	*/

	public String toString(){
		String tictactoeBoard = "";

		for (int i = 0; i<boardSize; i+=columns){
			String temp = "";
			for (int j = 0; j<columns; j++){
				if (board[i+j] == CellValue.EMPTY){
					if (j==0){
						temp = " " +  temp + " ";
					}
					else{
						temp = temp + " |  ";
					}
				}
				else{
					if (j==0){
						temp = " " + temp + board[i+j];
					}
					else{
						temp = temp + " | " + board[i+j];
					}
				}
			}
			if (!(i+columns >= boardSize)){
				temp = temp + "\n" + "----".repeat(columns);
			}
			tictactoeBoard = tictactoeBoard + temp + "\n";
		}
		return tictactoeBoard;
	}
}
