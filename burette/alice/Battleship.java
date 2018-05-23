package burette.alice;

import burette.alice.player.*;

public class Battleship {
    public static void main(String[] args) {



/*
        int[] coord = {11,0};



        for (int i = 0; i<20; i++) {
            coord = new int[]{(int) (Math.random() * 9.99), (int) (Math.random() * 9.99)};
            System.out.println(coord[0] + " - " + coord[1]);
            System.out.println(eia.coordToString(coord));
        }
*/

        AI eia = new EasyAI();
        AI eia2 = new EasyAI();
        AI mia = new MediumAI();
        AI hia = new HardcoreAI();
        AI uia = new UltraHardcoreAI();
        Game game = new Game(eia, eia2);


        //System.out.println(game.printBoard());
        for (Ship ship : eia.ships) {
            System.out.println(" ");
            for (int[] coord : ship.coord)
                System.out.println(coord[0] + " - " + coord[1] + " - " + coord[2]);
        }
///*
        //burette.alice.Game game = new burette.alice.Game(eia, eia2);
        //System.out.println(game.printBoard());

        int s1 = 0;
        int s2 = 0;
        for (int i = 0; i<1; i++) {
            IPlay res = game.autoGame();
            //System.out.println(res);
            if (res == eia) {
                s1++;
            }
            if (res == eia2)
                s2++;
        }

        System.out.println("score IA easy : "+s1);
        System.out.println("score IA medium : "+s2);
//*/

/*
        burette.alice.player.AI eia = new burette.alice.player.EasyAI();
        burette.alice.Ship ship = eia.ships[0];
        ship.coord[0][0] = 1;
        ship.coord[0][1] = 1;
        for (int i = 1; i < ship.getSize(); i ++) {
            ship.setCoord( i , new int[] { ship.getCoord()[i-1][0] + 1, ship.getCoord()[0][1], 0});
        }
        for (int n = 0; n < 3; n ++) {
            for (int i = 0; i < ship.getSize(); i++){
                System.out.print(ship.coord[i][n]+"  ");
            }
            System.out.println(" ");
        }
*/




/*
//TEST : burette.alice.Ship() / setShip / isHit / isDestroyed
        burette.alice.Ship s = new burette.alice.Ship(2);
        s.setShip("A1","A2");
        System.out.println(s.isHit("A3"));
        System.out.println(s.isDestroyed());
        System.out.println(s.isHit("A1"));
        System.out.println(s.isDestroyed());
        System.out.println(s.isHit("A2"));
        System.out.println(s.isDestroyed());

        EasyIA test = new EasyIA();
        for(int i=0; i<10; i++)
        System.out.println(test.shoot()[0]+" - "+test.shoot()[1]);
*/
//MAIN
/*

        System.out.println("test2");
        Scanner sc = new Scanner(System.in);
        //String entry = sc.nextLine();

        /*
        System.out.println("changer nb bateaux ? o/n");
        burette.alice.Game game;
        if( entry.equalsIgnoreCase("o")) {
            //TODO : changement des bateaux par défaut et création de la partie personnalisée
            int[] shipTest = new int[] {2};
            game = new burette.alice.Game(shipTest);
        }
        else{
            game = new burette.alice.Game();
        }


        burette.alice.Game game = new burette.alice.Game();
        for(burette.alice.player.IPlay player : game.getPlayers()) { //placement des bateaux
            System.out.println("le joueur "+((game.getRound()%2)+1)+" place ses bateaux");
            for(burette.alice.Ship ship : player.getShips()) {
                System.out.println("bateau de taille "+ship.getSize());
                System.out.println("Case de début : ");
                String startCoord = sc.nextLine();
                System.out.println("Case de fin : ");
                String endCoord = sc.nextLine();
                ship.setShip(startCoord, endCoord);
                game.endRound();
                //TODO : affichage de la map
            }
        }

        System.out.println("test1");
        burette.alice.player.Human h1 = new burette.alice.player.Human();
        burette.alice.player.Human h2 = new burette.alice.player.Human();
        burette.alice.Game game = new burette.alice.Game(h1,h2);
        h1.testSetShip();
        h2.testSetShip();

        while (game.currentPlayer().stillPlaying()) {
            System.out.println("tour du joueur "+ ((game.getRound()%2)+1));
            System.out.println("coordonnées tir : ");
            String entry = sc.nextLine();
            //entry = sc.nextLine();
            System.out.println(game.shoot(entry));
            game.endRound();
        }
        System.out.println("FIN DE PARTIE ! LE JOUEUR "+((game.getRound()+1)%2+1)+" GAGNE");
*/

    }
}
