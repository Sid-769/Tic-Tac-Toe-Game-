public class Configurations {

    private int board_size;
    private int lenghtToWin = 0;
    private int max_levels;
    private char[][] gameBoard;
    private HashDictionary dict;

    public Configurations(int board_size, int lenghtToWin, int max_levels) {
        this.board_size = board_size;
        this.lenghtToWin = lenghtToWin;
        this.max_levels = max_levels;

        gameBoard = new char[board_size][board_size];
        for (int row = 0; row < board_size; row++) {
            for (int col = 0; col < board_size; col++) {
                gameBoard[row][col] = ' ';
            }
        }
    }

    public HashDictionary createDictionary() {
        dict = new HashDictionary(6637);
        return dict;
    }

    public int repeatedConfiguration(HashDictionary configurations) {

        String sBoard = "";
        for (int row = 0; row < board_size; row++) {
            for (int col = 0; col < board_size; col++) {
                sBoard = sBoard + gameBoard[row][col];
            }
        }

        if (configurations.get(sBoard) != -1) {
            return configurations.get(sBoard);
        }
        return -1;
    }

    public void addConfiguration(HashDictionary configurations, int score) {

        String sBoard = "";
        for (int row = 0; row < board_size; row++) {
            for (int col = 0; col < board_size; col++) {
                sBoard = sBoard + gameBoard[row][col];
            }
        }

        try {
            configurations.put(new Data(sBoard, score));
        } catch (DictionaryException e) {
            System.out.println("Excpetion: DictEntry already exists.");
        }
    }

    public void savePlay(int row, int col, char symbol) {
        gameBoard[row][col] = symbol;
    }


    public boolean squareIsEmpty(int row, int col) {
        if (gameBoard[row][col] == ' ') {
            return true;
        } else return false;
    }


    public boolean wins(char symbol) {

        int k = 0;
        int d1 = 0;
        int d2 = 0;

        for (int row = 0; row < board_size; row++) {
            for (int col = 0; col < board_size; col++) {
                if (gameBoard[row][col] == symbol) {
                    k++;
                }
            }
            if (k == lenghtToWin) {
                return true;
            }
            k = 0;
        }

        for (int row = 0; row < board_size; row++) {
            for (int col = 0; col < board_size; col++) {
                if (gameBoard[col][row] == symbol) {
                    k++;
                }
            }
            if (k == lenghtToWin) {
                return true;
            }
            k = 0;
        }

        for (int row = 0; row < board_size; row++) {
            for (int col = 0; col < board_size; col++) {
                if ((row == col) && (gameBoard[row][col] == symbol)) {
                    d1++;
                }
            }
            if (d1 == lenghtToWin) {
                return true;
            }
        }

        for (int row = 0; row < board_size; row++) {
            for (int col = 0; col < board_size; col++) {
                if ((row + col == board_size - 1) && (gameBoard[row][col] == symbol)) {
                    d2++;
                }
            }
            if (d2 == lenghtToWin) {
                return true;
            }
        }

        return false;
    }

    public boolean isDraw() {
        boolean pass = true;

        for (int row = 0; row < board_size; row++) {
            for (int col = 0; col < board_size; col++) {
                if (gameBoard[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
        
    public int evalBoard() {
        if (wins('O')) {
            return 3;
        } else if (wins('X')) {
            return 0;
        } else if (isDraw()) {
            return 2;
        } else
            return 1;
    }

}