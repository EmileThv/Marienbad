/**
 * Game of Marienbad
 */
class MarienbadJvsJ_THEVENIN_LEROUX {

    private static final int MIN_LINES = 2;
    private static final int MAX_LINES = 15;

    void principal() {
        separateConsole();
        System.out.println("Bienvenue dans le jeu de Marienbad");

        // Obtenir les noms des joueurs
        String player1 = getPlayerName(1);
        String player2 = getPlayerName(2);

        // Obtenir le nombre de ligne de la table
        int n = getNumberOfRows();

        // Initialiser le plateau et le joueur actuel
        int[] game = initializeGame(n);
        String currentPlayer = player1;
        
        String winner;

        // Boucle principale
        while (!isGameOver(game)) {
            separateConsole();
            displayGame(game);
            System.out.println("------------[Joueur : " + currentPlayer + "]------------");

            // Obtenir et valider un mouvement
            int row;
            int numberOfMatches;
            do {
                row = askForRow(currentPlayer);
                numberOfMatches = askForNumberOfMatches(currentPlayer);
            } while (!removeMatches(game, row, numberOfMatches));

            // Inverser les joueurs
            if (currentPlayer.equals(player1)) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }

        // Réinverser les joueurs pour trouver le gagnant
        if (currentPlayer.equals(player1)) {
            winner = player2;
        } else {
            winner = player1;
        }
        System.out.println("Félicitations " + winner + ", vous avez gagné en prenant le dernier bâton");
    }

    /**
     * Get the player's name.
     * @param playerNumber The player's number (1 or 2)
     * @return The player's name
     */
    private String getPlayerName(int playerNumber) {
        System.out.print("Nom du joueur " + playerNumber + " : ");
        String name = SimpleInput.getString("> ");
        return name;
    }

    /**
     * Get the number of rows for the game.
     * @return The number of rows
     */
    private int getNumberOfRows() {
        int n;
        do {
            System.out.print("Entrez le nombre de lignes (entre " + MIN_LINES + " et " + MAX_LINES + ") : ");
            String input = SimpleInput.getString("> ");
            try {
                n = Integer.parseInt(input);
                if (n < MIN_LINES || n > MAX_LINES) {
                    System.out.println("Entrez un nombre valide de lignes");
                }
            } catch (NumberFormatException e) {
                n = -1;
                System.out.println("Entrée invalide. Entrez un nombre entier");
            }
        } while (n < MIN_LINES || n > MAX_LINES);
        return n;
    }

    /**
     * Initializes the game with the specified number of rows.
     * Each row follows the logical sequence: 1, 3, 5, 7, etc.
     * @param n Number of rows
     * @return Array representing the number of matches on each row
     */
    int[] initializeGame(int n) {
        int[] game = new int[n];
        for (int i = 0; i < n; i++) {
            game[i] = 2 * i + 1;
        }
        return game;
    }

    /**
     * Displays the current state of the game (number of matches on each row).
     * @param game Array representing the current state of the game
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
     * Separates console output with a series of dashes.
     */
    void separateConsole() {
        System.out.println("--------------------------------");
    }

    /**
     * Checks if the game is over, i.e., if there are no matches left.
     * @param game Array representing the current state of the game
     * @return True if all rows are empty, otherwise False
     */
    boolean isGameOver(int[] game) {
        boolean gameOver = true;
        for (int row : game) {
            if (row > 0) {
                gameOver = false;
            }
        }
        return gameOver;
    }

    /**
     * Removes a specified number of matches from a specified row.
     * @param game Array representing the current state of the game
     * @param row Row number from which to remove matches
     * @param numberOfMatches Number of matches to remove
     * @return True if the operation succeeded, otherwise False
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
     * Asks the player to choose a row.
     * @param player Current player
     * @return Row chosen by the player
     */
    int askForRow(String player) {
        System.out.print(player + ", choisissez une ligne : ");
        int row = SimpleInput.getInt("> ");
        return row;
    }

    /**
     * Asks the player to choose a number of matches to remove.
     * @param player Current player
     * @return Number of matches to remove
     */
    int askForNumberOfMatches(String player) {
        System.out.print(player + ", choisissez le nombre de bâtons à retirer : ");
        int numberOfMatches = SimpleInput.getInt("> ");
        return numberOfMatches;
    }
}
