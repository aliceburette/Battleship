package burette.alice;

import burette.alice.player.IPlay;

public class Game {

    // une partie peut être joueur contre joueur, ou joueur contre ia
    // la partie va stocker les règles : nb bateau et nb joueur (1 ou 2 = ia ou pas)

    private int round = 0;
    private IPlay[] players;

    public Game(IPlay player1, IPlay player2) {
        this.players = new IPlay[2];
        players[0] = player1;
        players[1] = player2;

    }

    /*
    public burette.alice.Game(int[] shipSizes) {
        this.players = new Player[2];
        players[0] = new Player(shipSizes);
        players[1] = new Player(shipSizes);
    }
    */

    public IPlay currentPlayer() {
        return this.players[round%2];
    }

    public IPlay adversePlayer() {
        return this.players[(round+1)%2];
    }

    public IPlay[] getPlayers() {
        return players;
    }

    public int getRound() {
        return round;
    }

    public void endRound() {
        round = round+1;
    }

    public String shoot(String missileCoord) {
        for(Ship ship : this.adversePlayer().getShips()) {
            if( ship.isHit(missileCoord) ) {
                if( ship.isDestroyed() ) {
                    recordResult("Coulé !");
                    return "Coulé !";
                }
                else {
                    recordResult("Touché !");
                    return "Touché !";
                }
            }
        }
        recordResult("Râté !");
        return "Râté !";
    }


    public IPlay autoGame() { // fonction pour générer une partie entre 2 IA
        while (this.currentPlayer().stillPlaying()) {
            //System.out.println("enter while");
            this.shoot(currentPlayer().target());
            this.endRound();
            //System.out.println(this.printBoard());
            //for (int [] coord : currentPlayer().getShips()[0].coord)
              //  System.out.println(coord[0]+" - "+coord[1]+" - "+coord[2]);
            //System.out.println(round);

        }
        IPlay winner = adversePlayer();
        IPlay temp = this.players[0];
        this.players[0] = this.players[1];
        this.players[1] = temp;
        this.resetGame();
        return winner;
    }


    private void recordResult(String result) {
        currentPlayer().setLastshotResult(result);

    }

    public void testResetGame() {
        this.round = 0;
    }

    public void resetGame() {
        this.round = 0;
        this.currentPlayer().reset();
        this.currentPlayer().autoShip();
        this.adversePlayer().reset();
        this.adversePlayer().autoShip();

    }

    public String printBoard() {
        String Newline=System.getProperty("line.separator");
        String[][] grid = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = "_";
            }
        }
        for (int [] shot : this.adversePlayer().getShots()) {
            grid[shot[0]][shot[1]] = "*";
        }
        for (Ship ship : this.currentPlayer().getShips()) {
            for (int[] coord : ship.getCoord()) {
                if ( grid[coord[0]][coord[1]] == "*") {
                    grid[coord[0]][coord[1]] = "X";
                }
                else {
                    grid[coord[0]][coord[1]] = "O";
                }
            }
        }
        String stringedGrid = "";
        for (int c=0; c < 10; c++) {
            stringedGrid = stringedGrid + Newline;
            for (int l=0; l < 10; l++) {
                stringedGrid = stringedGrid + grid[l][c] +" ";
            }
        }
        return stringedGrid;
    }
}
