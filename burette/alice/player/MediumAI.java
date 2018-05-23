package burette.alice.player;

public class MediumAI extends AI {

    @Override
    //TODO : corriger boucle infinie
    public String target() {
        boolean firstShot = true;
        int[] coord = new int[2];
        while (firstShot) {
            //System.out.println("while medium");
            firstShot = false;
            coord[0] = (int)(Math.random()*9.99);
            coord[1] = (int)(Math.random()*9.99);
            for (int loop = 0; loop < this.shots.size(); loop++) {
                if (coord[0] == this.shots.get(loop)[0] && coord[1] == this.shots.get(loop)[1]) {
                    firstShot = true;
                }
            }
        }
        this.shots.add(coord);
        return this.coordToString(coord);
    }
}
