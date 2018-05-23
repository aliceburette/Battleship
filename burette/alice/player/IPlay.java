package burette.alice.player;

import burette.alice.Ship;

import java.util.ArrayList;

public interface IPlay {

    Ship[] getShips();
    boolean stillPlaying();
    void setLastshotResult(String result);
    String target();
    void reset();
    void autoShip();
    ArrayList<int[]> getShots();


}