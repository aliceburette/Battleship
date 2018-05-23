package burette.alice;

import burette.alice.player.*;

import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Game game;
        boolean replay = true;
        System.out.println("BIENVENUE DANS CE JEU DE BATAILLE NAVALE !");
        System.out.println("1 - Faire une partie à deux joueurs");
        System.out.println("2 - jouer contre une IA");
        int mode = sc.nextInt();
        sc.nextLine();
        if (mode == 1) {      // jeu en mode PvP
            Human firstPlayer = new Human();
            Human secondPlayer = new Human();

            game = new burette.alice.Game(firstPlayer, secondPlayer);

        }
        else {         // jeu en mode joueur contre IA
            Human humanPlayer = new Human();
            AI aiPlayer;
            System.out.println("Choisir le niveau de difficulté : ");
            System.out.println("1 - Facile");
            System.out.println("2 - Moyen");
            System.out.println("3 - Hardcore");
            int level = sc.nextInt();
            sc.nextLine();
            if (level == 1) {
                aiPlayer = new EasyAI();
            } else {
                if (level == 2) {
                    aiPlayer = new MediumAI();
                } else {
                    aiPlayer = new HardcoreAI();
                }
            }

            game = new Game(humanPlayer, aiPlayer);
            System.out.println(game.printBoard());
        }

        while (replay) { // boucle d'une partie
            for (burette.alice.player.IPlay player : game.getPlayers()) { //placement des bateaux
                if (player instanceof Human) {
                    System.out.println("le joueur " + ((game.getRound() % 2) + 1) + " place ses bateaux");
                    System.out.println(game.printBoard());
                    for (burette.alice.Ship ship : player.getShips()) {
                        System.out.println("bateau de taille " + ship.getSize());
                        System.out.println("Case de début : ");
                        String startCoord = sc.nextLine();
                        System.out.println("Case de fin : ");
                        String endCoord = sc.nextLine();
                        ship.setShip(startCoord, endCoord);
                        System.out.println(game.printBoard());
                    }
                }
                else {
                    player.autoShip();
                }
                game.endRound();
            }
            while (game.currentPlayer().stillPlaying()) { // début du jeu
                for (int i = 0; i < 100; i++) {
                    System.out.println(" ");
                }
                if (game.currentPlayer() instanceof AI) {
                    game.shoot(game.currentPlayer().target());
                } else {
                    System.out.println("tour du joueur " + ((game.getRound() % 2) + 1));
                    System.out.println(game.printBoard());
                    System.out.println(" ");
                    System.out.println(game.printShotGrid());
                    System.out.println("coordonnées tir : ");
                    String entry = sc.nextLine();
                    System.out.println(game.shoot(entry));
                    ((Human) game.currentPlayer()).addShot(game.currentPlayer().getShips()[0].toInt(entry));
                }
                game.endRound();
            }
            System.out.println("FIN DE PARTIE ! LE JOUEUR " + ((game.getRound() + 1) % 2 + 1) + " GAGNE");
            game.resetGame();

            System.out.println("Voulez vous rejouer ?");
            System.out.println("1 - OUI");
            System.out.println("2 - NON");
            replay = (sc.nextInt() == 1);
        }
    }
}


