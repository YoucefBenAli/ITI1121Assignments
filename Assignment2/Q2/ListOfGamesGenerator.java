import java.util.LinkedList;

public class ListOfGamesGenerator {


	/**
	* generates all different games for the specified
	* parameters. Each game is recorded only once.
	* once a game is finished, it is not extended further
	* @param lines
	*  the number of lines in the game
	* @param columns
	*  the number of columns in the game
	* @param sizeWin
	*  the number of cells that must be aligned to win.
	* @return
	* a list of lists of game instances, ordered by levels
	*/
	public static LinkedList<LinkedList<TicTacToeGame>> generateAllGames(int lines, int columns, int winLength){

		TicTacToeGame original = new TicTacToeGame(lines,columns,winLength);
		LinkedList<LinkedList<TicTacToeGame>> list = new LinkedList<LinkedList<TicTacToeGame>>();
		LinkedList<TicTacToeGame> level0 = new LinkedList<TicTacToeGame>();

		level0.add(original);
		list.add(level0);
		boolean finished = false;
		while (!finished){
			LinkedList<TicTacToeGame> newLevel = new LinkedList<TicTacToeGame>();
			LinkedList<TicTacToeGame> currentLast = list.getLast();
			finished = true;
			for (int i = 0; i<currentLast.size();i++){
				TicTacToeGame currentBoard = currentLast.get(i);
				if (currentBoard.getGameState() == GameState.PLAYING){
					finished = false;
					for (int j = 0; j<lines*columns;j++){
						if (currentBoard.valueAt(j)==CellValue.EMPTY){
							TicTacToeGame newBoard = new TicTacToeGame(currentBoard, j);
							boolean copy = newLevel.contains(newBoard);

							if (!copy){
								newLevel.add(newBoard);
							}
						}
					}
				}
			}
			if (!finished){
				list.add(newLevel);
			}
		}



		return list;


	}
}
