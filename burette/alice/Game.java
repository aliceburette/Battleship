package burette.alice;

import burette.alice.player.IPlay;

public class Game {

    private int round = 0;
    private IPlay[] players;

    public Game(IPlay player1, IPlay player2) {
        this.players = new IPlay[2];
        players[0] = player1;
        players[1] = player2;

    }

    public IPlay currentPlayer() {
        return this.players[round%2];
    }

    private IPlay adversePlayer() {
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
            this.shoot(currentPlayer().target());
            this.endRound();
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

    public void resetGame() {
        this.round = 0;
        this.currentPlayer().reset();
        this.currentPlayer().autoShip();
        this.adversePlayer().reset();
        this.adversePlayer().autoShip();

    }

    public String printBoard() {
        String Newline=System.getProperty("line.separator");
        String[][] grid = new String[11][11];
        grid[0][0] = " ";
        int iterator = 1;
        for (char c = 'A'; c < 'K'; c++) {
            grid[iterator][0] = Character.toString(c);
            grid[0][iterator] = Integer.toString(iterator - 1);
            iterator++;
        }

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                grid[i][j] = "_";
            }
        }
        for (int [] shot : this.adversePlayer().getShots()) {
            grid[shot[0]+1][shot[1]+1] = "*";
        }
        for (Ship ship : this.currentPlayer().getShips()) {
            for (int[] coord : ship.getCoord()) {
                if (coord[0] != -1 && coord[1] != -1) {
                    if (grid[coord[0]+1][coord[1]+1] == "*") {
                        grid[coord[0]+1][coord[1]+1] = "X";
                    } else {
                        grid[coord[0]+1][coord[1]+1] = "O";
                    }
                }
            }
        }
        String stringedGrid = "";
        for (int c=0; c < 11; c++) {
            stringedGrid = stringedGrid + Newline;
            for (int l=0; l < 11; l++) {
                stringedGrid = stringedGrid + grid[l][c] +" ";
            }
        }
        return stringedGrid;
    }

    public String printShotGrid() {
        String Newline=System.getProperty("line.separator");
        String[][] grid = new String[11][11];
        grid[0][0] = " ";
        int iterator = 1;
        for (char c = 'A'; c < 'K'; c++) {
            grid[iterator][0] = Character.toString(c);
            grid[0][iterator] = Integer.toString(iterator - 1);
            iterator++;
        }
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                grid[i][j] = "_";
            }
        }
        for (int [] shot : this.currentPlayer().getShots()) {
            grid[shot[0]+1][shot[1]+1] = "*";
        }
        for (Ship ship : this.adversePlayer().getShips()) {
            for (int[] coord : ship.getCoord()) {
                if (grid[coord[0]+1][coord[1]+1] == "*") {
                    grid[coord[0]+1][coord[1]+1] = "X";
                }
            }
        }
        String stringedGrid = "";
        for (int c=0; c < 11; c++) {
            stringedGrid = stringedGrid + Newline;
            for (int l=0; l < 11; l++) {
                stringedGrid = stringedGrid + grid[l][c] +" ";
            }
        }
        return stringedGrid;
    }
}
