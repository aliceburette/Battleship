package burette.alice;

import com.sun.xml.internal.bind.v2.TODO;

public class Ship {
    public int size;
    public int coord[][];

    public Ship(int size) {
        this.size = size;
        this.coord = new int[size][3];
    }

    public int[][] getCoord() {
        return coord;
    }

    public void setCoord(int num , int[] coord) {
        this.coord[num] = coord;
    }

    public int getSize() {
        return size;
    }

    public void setShip(String startCoord, String endCoord) {
        int [] sCoord = toInt(startCoord);
        int [] eCoord = toInt(endCoord);
        if( sCoord[0] == eCoord[0] ) {
            for( int i=0; i<this.size; i++) {
                this.coord[i][0] = sCoord[0];
                this.coord[i][1] = sCoord[1]+i;
                this.coord[i][2] = 0;
            }
        }
        else {
            for( int i=0; i<this.size; i++) {
                this.coord[i][0] = sCoord[0]+i;
                this.coord[i][1] = sCoord[1];
                this.coord[i][2] = 0;
            }

        }
    }

    // dit si le bateau est touché par le missile et modifie le bateau si c'est le cas
    public boolean isHit(String missileCoord){
        int[] mCoord = toInt(missileCoord);
        int i = 0;
        boolean hit = false;
        while(hit == false && i<size) {
            if( (mCoord[0] == this.coord[i][0]) && (mCoord[1] == this.coord[i][1]) ) {
                hit = true;
                this.coord[i][2] = 1;
            }
            i = i+1;
        }
        return hit;
    }

    // dit si le bateau est détruit (ie si toutes ses cases sont touchées)
    public boolean isDestroyed(){
        boolean destroyed = true;
        int i = 0;
        while( destroyed == true && i<this.size ) {
            if( this.coord[i][2] == 0 ) {
                destroyed = false;
            }
            i = i+1;
        }
        return destroyed;
    }

    public int[] toInt(String coord) {
        char temp = coord.charAt(0);
        int sCoord[] = new int[2];
        sCoord[1] = Integer.parseInt(coord.substring(1));
        sCoord[0] = (int) temp-('A'-1);
        return sCoord;
    }

    public void resetShip(){
        for (int[] coordo : this.coord) {
            coordo = new int[]{-1, -1, 0};
        }
    }
}
