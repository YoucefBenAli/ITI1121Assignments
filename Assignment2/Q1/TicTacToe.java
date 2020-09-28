public class TicTacToe{

    /**
    * <b>main</b> of the application. Creates the instance of  GameController
    * and starts the game. If two parameters line  and column
    * are passed, they are used.
    * Otherwise, a default value is used. Defaults values are also
    * used if the paramters are too small (less than 2).
    *
    * @param args
    *            command line parameters
    */
    public static void main(String[] args) {

        StudentInfo.display();

        TicTacToeGame game;
        int lines = 3;
        int columns = 3;
        int win = 3;


        try{
            if (args.length >= 2) {
                lines = Integer.parseInt(args[0]);
                if(lines<2){
                    System.out.println("Invalid argument, using default...");
                    lines = 3;
                }
                columns = Integer.parseInt(args[1]);
                if(columns<2){
                    System.out.println("Invalid argument, using default...");
                    columns = 3;
                }
            }
            if (args.length >= 3){
                win = Integer.parseInt(args[2]);
                if(win<2){
                    System.out.println("Invalid argument, using default...");
                    win = 3;
                }
            }
            if (args.length > 3){
                System.out.println("Too many arguments. Only the first 3 are used.");
            }

        } catch(NumberFormatException e){
            System.out.println("Invalid argument, using default...");
            lines   = 3;
            columns  = 3;
            win = 3;
        }

        Player human = new HumanPlayer();
        Player AI = new ComputerRandomPlayer();

        Player[] players = {human,AI};
        boolean playAgain = true;

        Player player1 = players[0]; // Human
        Player player2 = players[1]; // AI

        int player1Turn = 0;
        int player2Turn = 1;

        int[] turns = {player1Turn, player2Turn};

        int turn = turns[Utils.generator.nextInt(2)];

        while (playAgain) {
            game = new TicTacToeGame(lines,columns,win);
            int originalTurn = turn;
            while(game.getGameState() == GameState.PLAYING){
                if (game.getLevel()%2 == 0){
                    if (turn == 0){
                        System.out.println("Player 1's turn");
                        System.out.println(game.toString());
                        player1.play(game);
                        turn = 1;
                    }
                    else {
                        System.out.println("Player 2's turn");
                        player2.play(game);
                        turn = 0;
                    }
                }
                else{
                    if (turn == 0){
                        System.out.println("Player 1's turn");
                        System.out.println(game.toString());
                        player1.play(game);
                        turn = 1;
                    }
                    else {
                        System.out.println("Player 2's turn");
                        player2.play(game);
                        turn = 0;
                    }
                }
            }

            System.out.println(game.toString());
            System.out.println(game.getGameState());

            String playAgainResponse = Utils.console.readLine("Play again (Y/N)?: ");

            while (!(playAgainResponse.equals("y") || playAgainResponse.equals("Y") || playAgainResponse.equals("n") || playAgainResponse.equals("N"))) {
                System.out.println("Invalid response. Please type 'Y' for yes or 'N' for no");
                String playAgainResponse2 = Utils.console.readLine("Play again (Y/N)?: ");
                playAgainResponse = playAgainResponse2;
            }

            if (playAgainResponse.equals("y") || playAgainResponse.equals("Y")) {
                playAgain = true;

                if (turn == originalTurn) {
                    if (turn == 0) {
                        turn = 1;
                    }
                    else {
                        turn = 0;
                    }
                }
            }
            else if (playAgainResponse.equals("n") || playAgainResponse.equals("N")) {
                playAgain = false;
            }
        }

    }

}
