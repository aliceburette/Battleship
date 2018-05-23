package burette.alice.player;

import burette.alice.Ship;

import java.util.ArrayList;

public class Human implements IPlay {

    Ship[] ships;
    protected ArrayList<int[]> shots = new ArrayList<int[]>();

    public Human() {
        this.ships = new Ship[5];
        this.ships[0] = new Ship(5);
        this.ships[1] = new Ship(4);
        this.ships[2] = new Ship(3);
        this.ships[3] = new Ship(3);
        this.ships[4] = new Ship(2);
    }

    public Human(int[] shipSizes) {  // fonction plus utilisée, sert à modifier le nombre de bateaux et leur taille
        this.ships = new Ship[shipSizes.length];
        for(int i=0; i<shipSizes.length; i++ ) {
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
        for(Ship ship : this.ships) {
            if (!ship.isDestroyed())
                bool = true;
        }
        return bool;
    }

    @Override
    public void setLastshotResult(String result) {
    }

    @Override
    public String target() {
        return null;
    }

    @Override
    public void reset() {}

    @Override
    public void autoShip() {}
}
