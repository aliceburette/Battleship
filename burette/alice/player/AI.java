package burette.alice.player;

import burette.alice.Ship;

import java.util.ArrayList;

public abstract class AI implements IPlay {

    public Ship[] ships;
    ArrayList<int[]> shots = new ArrayList<int[]>();

    public AI() {
        this.ships = new Ship[5];
        this.ships[0] = new Ship(5);
        this.ships[1] = new Ship(4);
        this.ships[2] = new Ship(3);
        this.ships[3] = new Ship(3);
        this.ships[4] = new Ship(2);
        this.reset();
        this.autoShip();
    }

    public AI(int[] shipSizes) {  // fonction plus utilisée, sert à modifier le nombre de bateaux et leur taille
        this.ships = new Ship[shipSizes.length];
        for (int i = 0; i < shipSizes.length; i++) {
            this.ships[i] = new Ship(shipSizes[i]);
        }
    }

    @Override
    public ArrayList<int[]> getShots() {
        return shots;
    }

    public Ship[] getShips() {
        return ships;
    }

    public boolean stillPlaying() {
        boolean bool = false;
        for (Ship ship : this.ships) {
            if (!ship.isDestroyed())
                bool = true;
        }
        return bool;
    }

    public String coordToString(int[] target) {
        char matchingLetter = ' ';
        char pointerLetter = 'A';
        while (matchingLetter == ' ') {
            if (((int) pointerLetter) - 65 == target[0]) {
                matchingLetter = pointerLetter;
            } else
                pointerLetter++;
        }
        return Character.toString(matchingLetter) + Integer.toString(target[1]);
    }

    @Override
    public void setLastshotResult(String result) {
    }

    @Override
    public void reset() {
        for (Ship ship : this.ships) {
           ship.resetShip();
        }
        this.shots = new ArrayList<int[]>();
    }

    public boolean checkCoord(int[] coordToCheck) {
        for (Ship ship : this.ships) {
            for (int[] coord : ship.getCoord()) {
                if ( coordToCheck[0] == coord[0] && coordToCheck[1] == coord[1]) {
                    return false;
                }
            }
        }
        return true;
    }
    public void autoShip() {
/*
        boolean notChecked;
        int orientation;
        int[] coord;
        for (Ship ship : this.ships) {
            notChecked = true;
            while (notChecked) {
                ship.resetShip();
                int[] coordToCheck;
                orientation = (int) (Math.random() * 1.99);
                if (orientation == 0) { // on place le bateau horizontalement vers la droite
                    coordToCheck = new int[]{(int) (Math.random() * (9.99 - ship.getSize())), (int) (Math.random() * 9.99), 0};
                    notChecked = !checkCoord(coordToCheck);
                    ship.setCoord(0, coordToCheck); // on choisi la première coordonnée au hasard
                    if (!notChecked) {
                        for (int i = 1; i < ship.getSize(); i++) {
                            coordToCheck = new int[]{ship.getCoord()[i - 1][0] + 1, ship.getCoord()[0][1], 0};
                            if (!checkCoord(coordToCheck)) {
                                notChecked = true;
                            }
                            ship.setCoord(i, coordToCheck);
                        }
                    }
                }
                else { // on place le bateau verticalement vers le bas
                    coordToCheck = new int[]{(int) (Math.random() * 9.99 ), (int) (Math.random() * (9.99- ship.getSize())), 0};
                    notChecked = !checkCoord(coordToCheck);
                    ship.setCoord(0, coordToCheck);
                    if (!notChecked) {
                        for (int i = 1; i < ship.getSize(); i++) {
                            coordToCheck = new int[]{ship.getCoord()[0][0], ship.getCoord()[i - 1][1] + 1, 0};
                            notChecked = !checkCoord(coordToCheck);
                            ship.setCoord(i, coordToCheck);

                        }
                    }
                }
            }
        }
*/

///*
            this.ships[0].setShip("A0", "E0");
            this.ships[1].setShip("D1", "D4");
            this.ships[2].setShip("F2", "F4");
            this.ships[3].setShip("H2", "H4");
            this.ships[4].setShip("H6", "H8");
//*/
    }
}
