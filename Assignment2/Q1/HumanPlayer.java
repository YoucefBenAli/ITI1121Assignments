public class HumanPlayer implements Player {
    public void play(TicTacToeGame game) {
        if (game.getGameState() == GameState.PLAYING){
            boolean played = false;
            while (!played){
                int playerChoice;

                int play = game.getLevel();

                if (play%2 == 0){
                    playerChoice = Integer.parseInt(Utils.console.readLine("X to play: ")) -1;
                }
                else{
                    playerChoice = Integer.parseInt(Utils.console.readLine("O to play: ")) -1;
                }

                if (playerChoice<game.columns*game.lines  && playerChoice>=0 && game.valueAt(playerChoice)== CellValue.EMPTY){
                    played = true;
                    game.play(playerChoice);
                }
                else{
                    if(playerChoice>game.columns*game.lines - 1 || playerChoice<0){
                        System.out.println("The value should be between 1 and " + game.columns*game.lines);
                    }
                    else{
                        System.out.println("This cell has already been played");
                    }
                }

                if (game.getGameState() != GameState.PLAYING){
                    break;
                }
            }
        }

        else{
            System.out.println("The game is not playable.");
        }
    }
}
