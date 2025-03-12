/**
 * Game of Marienbad
 */
class MarienbadVsIa {

    private static final int MIN_LINES = 2;
    private static final int MAX_LINES = 15;

    private int difficultyLevel = -1;

    void principal() {
		testRemoveMatches();
        separateConsole();
        System.out.println("Bienvenue dans le jeu de Marienbad");

        String player1 = getPlayerName(1);
        String player2 = "Bot";

        chooseDifficultyLevel();

        int n = getNumberOfRows();

        int[] matches = initializeGame(n);
        String currentPlayer = player1;

        String winner;

        int[] move;

        while (!isGameOver(matches)) {
            separateConsole();
            displayGame(matches);
            System.out.println("------------[Joueur : " + currentPlayer + "]------------");

            int row;
            int numberOfMatches;
            do {
                if (currentPlayer.equals("Bot")) {
                    move = askBotForMove(matches);
                } else {
                    move = askPlayerForMove(currentPlayer);
                }

                row = move[0];
                numberOfMatches = move[1];

                if (currentPlayer.equals("Bot")) {
                    System.out.println(currentPlayer + " has taken " + numberOfMatches + " from row " + row + ".");
                }

            } while (!removeMatches(matches, row, numberOfMatches));

            if (currentPlayer.equals(player1)) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }

        if (currentPlayer.equals(player1)) {
            winner = player2;
        } else {
            winner = player1;
        }
        System.out.println("Felicitations " + winner + ", vous avez gagne en prenant le dernier baton");
    }

    void chooseDifficultyLevel() {
        do {
            System.out.println("Choisissez le niveau de difficulte :");
            System.out.println("1. Facile");
            System.out.println("2. Moyen");
            System.out.println("3. Difficile");
            difficultyLevel = SimpleInput.getInt("> ");
            if (difficultyLevel != -1 && (difficultyLevel < 1 || difficultyLevel > 3)) {
                System.out.println("Niveau de difficulte invalide. Veuillez entrer un nombre entre 1 et 3.");
            }
        } while (difficultyLevel < 1 || difficultyLevel > 3);
    }

    /**
     * Obtient le nom du joueur.
     *
     * @param playerNumber Le numéro du joueur (1 ou 2)
     * @return Le nom du joueur
     */
    private String getPlayerName(int playerNumber) {
        System.out.print("Nom du joueur " + playerNumber + " : ");
        String name = SimpleInput.getString("> ");
        return name;
    }

    /**
     * Obtient le nombre de lignes pour le jeu.
     *
     * @return Le nombre de lignes
     */
    private int getNumberOfRows() {
        int lines;
        do {
            System.out.print("Entrez le nombre de lignes (entre " + MIN_LINES + " et " + MAX_LINES + ") : ");
            lines = SimpleInput.getInt("> ");
            if (lines < MIN_LINES || lines > MAX_LINES) {
                System.out.println("Entrez un nombre valide de lignes");
            }
        } while (lines < MIN_LINES || lines > MAX_LINES);
        return lines;
    }
    
    //////////////////////////////////

    /**
     * Initialise le jeu avec le nombre de lignes spécifié.
     * Chaque ligne suit la séquence logique : 1, 3, 5, 7, etc.
     *
     * @param n Nombre de lignes
     * @return Tableau représentant le nombre d'allumettes sur chaque ligne
     */
    int[] initializeGame(int n) {
        int[] game = new int[n];
        for (int i = 0; i < n; i++) {
            game[i] = 2 * i + 1;
        }
        return game;
    }

    /**
     * Affiche l'état actuel du jeu (nombre d'allumettes sur chaque ligne).
     *
     * @param game Tableau représentant l'état actuel du jeu
     */
    void displayGame(int[] game) {
        for (int i = 0; i < game.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < game[i]; j++) {
                System.out.print("| ");
            }
            System.out.println();
        }
    }

    /**
     * Sépare la sortie de la console avec une série de tirets.
     */
    void separateConsole() {
        System.out.println("--------------------------------");
    }


	//////////////////////
	
    /**
     * Vérifie si le jeu est terminé, c'est-à-dire s'il n'y a plus d'allumettes.
     *
     * @param game Tableau représentant l'état actuel du jeu
     * @return Vrai si toutes les lignes sont vides, sinon Faux
     */
    boolean isGameOver(int[] game) {
        boolean gameOver = true;
        for (int i = 0; i < game.length; i++) {
            int row = game[i];
            if (row > 0) {
                gameOver = false;
            }
        }
        return gameOver;
    }


	void testRemoveMatches () {
		int [] game1 = initializeGame(3);
		int [] game2 = initializeGame(6);
		testCasRemoveMatches(game1,2,31,false);
		testCasRemoveMatches(game2,3,5,true);
	}
	
	void testCasRemoveMatches (int [] game, int row, int nbMatches, boolean result){
		boolean execRes = removeMatches(game,row,nbMatches);
		if (execRes == result &&  (result^!(game[row] == (row*2)+1-nbMatches))){
			System.out.println("testCasRemoveMatches : OK");
		}else{
			System.out.println("testCasRemoveMatches : Non :(");
		}
	}
			
    /**
     * Retire un nombre prédéfini d'allumettes d'une ligne prédéfinie du jeu
     * @param game            tableau représentant le jeu
     * @param row             ligne d'où enlever les allumettes
     * @param numberOfMatches Nombre d'allumettes à retirer
     * @return Vrai si les allumettes ont pu être enlevées, faux sinon
     */
    boolean removeMatches(int[] game, int row, int numberOfMatches) {
        boolean operationSuccessful = false;
        if (row >= 0 && row < game.length && numberOfMatches > 0 && numberOfMatches <= game[row]) {
            game[row] -= numberOfMatches;
            operationSuccessful = true;
        }
        return operationSuccessful;
    }

    /**
     * Demande au joueur de choisir une ligne.
     *
     * @param player Joueur actuel
     * @return Mouvement composé d'une ligne et d'un nombre d'allumettes choisi par le joueur
     */
    int[] askPlayerForMove(String player) {
        int[] move = new int[2];

        System.out.print(player + ", choisissez une ligne : ");
        move[0] = SimpleInput.getInt("> ");

        System.out.print(player + ", choisissez le nombre de bâtons à retirer : ");
        move[1] = SimpleInput.getInt("> ");

        return move;
    }

/////////////////////

    /**
     * Demande au bot de choisir une ligne.
     *
     * @param matches État actuel du jeu
     * @return Mouvement composé d'une ligne et d'un nombre d'allumettes choisi par le bot
     */
    int[] askBotForMove(int[] matches) {
        int[] move = {-1, -1};
        if (difficultyLevel == 1) {
            move = askBotForRandomMove(matches);
        } else if (difficultyLevel == 2) {
            move = askBotForMiddleMove(matches);
        } else if (difficultyLevel == 3) {
            move = askBotForWinningMove(matches);
        }
        return move;
    }

//////////////////////////////

    int[] askBotForRandomMove(int[] matches) {
        int[] move = new int[2];

        int row = (int) (Math.random() * matches.length);
        int numberOfMatches = (int) (Math.random() * matches[row]);

        move[0] = row;
        move[1] = numberOfMatches;

        return move;
    }

/////////////////////////////

    int[] askBotForMiddleMove(int[] matches) {
        int[] move = askBotForRandomMove(matches);

        move[0] = 0;
        move[1] = 0;

        return move;
    }

/////////////////////////////

    int[] askBotForWinningMove(int[] matches) {
        int[] move = askBotForRandomMove(matches);
        boolean [][] tab = toBinarytwodtab(matches);
        for (int i = 0; i < matches.length; i++) {
            if (matches[i] > 0) {
                for (int j = 1; j <= matches[i]; j++) {
                    int[] temp = matches.clone();
                    temp[i] -= j;
                    if (!paritySumTab(toBinarytwodtab(temp))) {
                        move = new int[]{i, j}; // Trouver un mouvement gagnant
                    }
                }
            }
        }
        return move;
    }

//////////////////

    boolean paritySumTab(boolean[][] tab) {
        boolean[] nimSum = new boolean[tab[0].length];
        boolean result = false;

        for (int i = 0; i < tab[0].length; i++) {
            for (int j = 0; j < tab.length; j++) {
                nimSum[i] ^= tab[j][i];
            }
        }

        for (boolean b : n) {
            result ^= b;
        }

        return result;
    }

    boolean[][] toBinarytwodtab(int[] matches) {
        boolean[][] tab = new boolean[matches.length][];
        for (int i = 0; i < matches.length; i++) {
            tab[i] = new boolean[matches[i]];
            for (int j = 0; j < matches[i]; j++) {
                tab[i][j] = true;
            }
        }
        return tab;
    }
}

