
public class AI {
    private static int[][] board = new int[7][7];
    public static long startTime = 0;
    private static final int[] COLCHECKS = { 3, 4, 2, 5, 1, 0, 6 };
    private static final int MINTIME = 3000; // minimum time used to search

    /**
     * minimax algorithm with alpha-beta pruning
     * 
     * @param aiPos            the current position of the ai
     * @param playerPos        the current position of the player
     * @param alpha            the current alpha value
     * @param beta             the curent beta value
     * @param mask             the mask
     * @param depth            the depth to be searched
     * @param maximizingPlayer true if the pos is to be maxed, false if to be
     *                         minimized
     * @return {heurisitic value of position, column in which this occurs}
     */
    public static int[] minimax(long aiPos, long playerPos, int alpha, int beta, long mask, int depth,
            boolean maximizingPlayer) {
        int terminalAI = isTerminal(aiPos, mask);
        int terminalPlayer = isTerminal(playerPos, mask);
        if (depth == 0 || terminalAI != -1 || terminalPlayer != -1) {
            if (terminalAI == 1) {
                return new int[] { Integer.MAX_VALUE - (42 - depth), 0 };
            } else if (terminalPlayer == 1) {
                return new int[] { Integer.MIN_VALUE + (42 - depth), 0 };
            } else if (terminalAI == 0) {
                return new int[] { 0, 0 };
            } else {
                if (System.currentTimeMillis() - startTime > MINTIME) {
                    return new int[] { evalBoard(Long.toBinaryString(aiPos), Long.toBinaryString(playerPos)), 0 };
                } else {

                    return minimax(aiPos, playerPos, alpha, beta, mask, 1, maximizingPlayer);
                }
            }
        }

        if (maximizingPlayer) {
            int value = Integer.MIN_VALUE;
            int column = 0;
            for (int i : COLCHECKS) {
                if (Board.canPlay(i, mask)) {
                    long[] newAIBoard = Board.makeMove(i, mask, aiPos, playerPos);
                    int newScore = minimax(newAIBoard[1], playerPos, alpha, beta, newAIBoard[0], depth - 1, false)[0];
                    if (newScore > value) {
                        value = newScore;
                        column = i;
                    }
                    alpha = Math.max(alpha, value);
                    if (alpha >= beta) {
                        break;
                    }
                }
            }
            return new int[] { value, column };
        } else {
            int value = Integer.MAX_VALUE;
            int column = 0;
            for (int i : COLCHECKS) {
                if (Board.canPlay(i, mask)) {
                    long[] newPlayerBoard = Board.makeMove(i, mask, playerPos, aiPos);
                    int newScore = minimax(aiPos, newPlayerBoard[1], alpha, beta, newPlayerBoard[0], depth - 1,
                            true)[0];
                    if (newScore < value) {
                        value = newScore;
                        column = i;
                    }
                    beta = Math.min(beta, value);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return new int[] { value, column };
        }
    }

    /**
     * checks if the position has a result (win/loss/draw)
     * 
     * @param pos  the position to be checked
     * @param mask the mask
     * @return 1 if win is on the board, 0 if drawn
     */
    public static int isTerminal(long pos, long mask) {
        return Board.checkState(pos, mask);
    }

    /**
     * evaluates the position given that the position is not terminal
     * 
     * @param playerPos the player position
     * @param aiPos     the ai position
     * @return the value of the board
     */
    public static int evalBoard(String playerPos, String aiPos) {

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (((i * 7) + j) < playerPos.length()) {
                    board[j][i] += playerPos.charAt(playerPos.length() - ((i * 7) + j) - 1) == '1' ? 1 : 0;
                }

                if (((i * 7) + j) < aiPos.length()) {
                    board[j][i] += aiPos.charAt(aiPos.length() - ((i * 7) + j) - 1) == '1' ? 1 : 0;
                }
            }

        }
        int value = InstanPosEval.evaluatePosition(board);
        board = new int[7][7];

        return value;
    }
}
