package burette.alice.player;

public class UltraHardcoreAI extends AI {

    int[] lastRandomHit = null;
    String lastshotResult;

    @Override
    public String target() {
        int[] coord = new int[2];
        if (shots.size() != 0) {
            if (lastRandomHit == shots.get(shots.size() - 1) && lastshotResult == "Râté !") {
                lastRandomHit = null;
            }
        }
        if (lastRandomHit != null) {
            // dernier tir --> shots.get(shots.size()-1)
            if (lastshotResult == "Coulé !") {
                lastRandomHit = null; // on réinitialise lastRandomHit pour chercher un nouveau bateau en random
            } else {
                if (shots.get(shots.size() - 1)[0] == lastRandomHit[0]) {
                    if (shots.get(shots.size() - 1)[1] >= lastRandomHit[1]) {
                        // zone 1
                        if (lastshotResult == "Touché !" && (shots.get(shots.size() - 1)[1] + 1) < 10) {
                            coord[0] = lastRandomHit[0];
                            coord[1] = shots.get(shots.size() - 1)[1] + 1;
                        } else {
                            if (lastRandomHit[1] > 0) {
                                // le dernier tir est râté
                                coord[0] = lastRandomHit[0];
                                coord[1] = lastRandomHit[1] - 1;

                            }
                        }
                    } else {
                        // zone 3
                        if (lastshotResult == "Touché !" && (shots.get(shots.size() - 1)[1]) > 0) {
                            coord[0] = lastRandomHit[0];
                            coord[1] = shots.get(shots.size() - 1)[1] - 1;
                        } else { // le dernier tir est râté
                            if ((lastRandomHit[0] + 1) < 10) {
                                coord[0] = lastRandomHit[0] + 1;
                                coord[1] = lastRandomHit[1];
                            }
                        }
                    }
                } else {
                    if (shots.get(shots.size() - 1)[0] > lastRandomHit[0]) {
                        // zone 2
                        if (lastshotResult == "Touché !" && (shots.get(shots.size() - 1)[0] + 1) < 10) {
                            coord[0] = shots.get(shots.size() - 1)[0] + 1;
                            coord[1] = lastRandomHit[1];
                        } else { // le dernier tir est râté
                            if (lastRandomHit[0] > 0) {
                                coord[0] = lastRandomHit[0] - 1;
                                coord[1] = lastRandomHit[1];
                            }
                        }
                    } else {
                        // zone 4
                        if (shots.get(shots.size() - 1)[0] > 0) {
                            coord[0] = shots.get(shots.size() - 1)[0] - 1;
                            coord[1] = lastRandomHit[1];
                        }
                    }
                }
            }
        }
        if (lastRandomHit == null) {
            boolean firstShot = true;
            while (firstShot) {
                firstShot = false;
                int parity = (int) (Math.random() * 1.99);
                coord[0] = ((int) (Math.random() * 4.99)) * 2 + parity;
                coord[1] = ((int) (Math.random() * 4.99)) * 2 + parity;
                for (int loop = 0; loop < this.shots.size(); loop++) {
                    if (coord[1] == this.shots.get(loop)[1] && coord[0] == this.shots.get(loop)[0]) {
                        firstShot = true;
                    }
                }
            }
            this.lastRandomHit = coord;
        }
        this.shots.add(coord);
        return this.coordToString(coord);
    }

    public void setLastshotResult(String lastshotResult) {
        this.lastshotResult = lastshotResult;
    }

    @Override
    public void reset() {
        super.reset();
        this.lastRandomHit = null;

    }
}

