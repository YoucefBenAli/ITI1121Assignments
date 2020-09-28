
public class ComputerRandomPlayer implements Player {
    public void play(TicTacToeGame game) {
        if (game.getGameState() == GameState.PLAYING){
            boolean played = false;
            while (!played){
                int choice;
                choice = Utils.generator.nextInt(game.columns*game.lines);
                if (choice<game.columns*game.lines  && choice>=0 && game.valueAt(choice) == CellValue.EMPTY){
                    played = true;
                    game.play(choice);
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
