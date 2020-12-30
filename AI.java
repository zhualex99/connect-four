
public class AI {
    private static int[][] board = new int[7][7];
    private static final int[] COLCHECKS = {3, 4, 2, 5, 1, 6, 0};
    public static int[] minimax (long aiPos,long playerPos, int alpha, int beta, long mask, int depth, boolean maximizingPlayer){
        int terminalAI = isTerminal(aiPos);
        int terminalPlayer = isTerminal(playerPos);
        if (depth == 0 || terminalAI!=-1 || terminalPlayer!=-1){
            if (terminalAI==1){
                return new int[] {Integer.MAX_VALUE-(42-depth), 0};
            }
            else if (terminalPlayer==1){
                return new int[] {Integer.MIN_VALUE+(42-depth), 0};
            }
            else{
                return new int[] {evalBoard(Long.toBinaryString(aiPos), Long.toBinaryString(playerPos)), 0};
                
            }
        }

        if(maximizingPlayer){
            int value = Integer.MIN_VALUE;
            int column = 0;
            for(int i : COLCHECKS){
                if(Board.canPlay(i, mask)){
                long[] newAIBoard = Board.makeMove(i, mask, aiPos, playerPos);
                int newScore = minimax(newAIBoard[1], playerPos, alpha, beta, newAIBoard[0], depth-1, false)[0];
                if(newScore>value){
                    value = newScore;
                    column = i;
                }
                alpha = Math.max(alpha, value);
                if (alpha >= beta){
                    break;
                }
            }
            }
            return new int[] {value, column};
        }
        else {
            int value = Integer.MAX_VALUE;
            int column = 0;
            for(int i : COLCHECKS){
                if(Board.canPlay(i, mask)){
                long[] newPlayerBoard = Board.makeMove(i, mask, playerPos, aiPos);
                int newScore = minimax(aiPos, newPlayerBoard[1], alpha, beta, newPlayerBoard[0], depth-1, true)[0];
                if(newScore<value){
                    value = newScore;
                    column = i;
                }
                beta = Math.min (beta, value);
                if(beta<= alpha){
                    break;
                }
            }
            }
            return new int[] {value, column};
        }
    }

    public static int isTerminal(long pos){
        return Board.checkState(pos);
    }


    public static int evalBoard(String playerPos, String aiPos){
        
        for(int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++){
                if(((i*7)+j)<playerPos.length()){
                    board[j][i] += playerPos.charAt (playerPos.length()-((i*7)+j)-1)=='1'? 1 : 0;
                }

                if(((i*7)+j)<aiPos.length()){
                    board[j][i] += aiPos.charAt (aiPos.length()-((i*7)+j)-1)=='1'? 1 : 0;
                }
                
                

            }

        }
        int value =  Test.evaluatePosition(board);
        board = new int[7][7];
        
        return value;
    }
}
