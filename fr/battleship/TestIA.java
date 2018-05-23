package fr.battleship;

import burette.alice.*;
import burette.alice.player.*;

public class TestIA {
    public static void main(String[] args) {

        AI eia = new EasyAI();
        AI mia = new MediumAI();
        AI hia = new HardcoreAI();
        AI uia = new UltraHardcoreAI();
        System.out.println("burette.alice.player.AI created");

        Game game1 = new Game(eia, mia);
        System.out.println(eia.ships[1].coord[1][0]);
        System.out.println(game1.printBoard());
        int s1 = 0;
        int s2 = 0;

        for (int i = 0; i<100; i++) {
            IPlay res = game1.autoGame();
            if (res == eia) {
                s1++;
            }
            if (res == mia)
                s2++;
        }
        System.out.println("easy VS medium");
        System.out.println("score IA easy : "+s1);
        System.out.println("score IA medium : "+s2);
        System.out.println(" ");

        Game game2 = new Game(eia, uia);
        s1 = 0;
        s2 = 0;
        for (int i = 0; i<100; i++) {
            IPlay res = game2.autoGame();
            if (res == eia) {
                s1++;
            }
            if (res == uia)
                s2++;
        }
        System.out.println("easy VS hardcore");
        System.out.println("score IA easy : "+s1);
        System.out.println("score IA hardcore : "+s2);
        System.out.println(" ");

        Game game3 = new Game(mia, uia);
        s1 = 0;
        s2 = 0;
        for (int i = 0; i<100; i++) {
            IPlay res = game3.autoGame();
            if (res == mia) {
                s1++;
            }
            if (res == uia)
                s2++;
        }
        System.out.println("medium VS hardcore");
        System.out.println("score IA medium : "+s1);
        System.out.println("score IA hardcore : "+s2);
        System.out.println(" ");

    }
}
