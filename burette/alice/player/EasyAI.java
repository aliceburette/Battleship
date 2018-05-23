package burette.alice.player;

public class EasyAI extends AI {

    @Override
    public String target() {
        int[] coord = {(int)(Math.random()*9.99), (int)(Math.random()*9.99)};
        this.shots.add(coord);
        return this.coordToString(coord);
    }
}
