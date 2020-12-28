public class AI {
    public static int minimax (long aiPos,long playerPos, long mask, int depth, boolean maximizingPlayer){
        int value=0;
        if(depth == 0 || Board.checkState(aiPos) !=-1 || Board.checkState(playerPos)!=-1){ // if result is on board or leaf node
            
            if(depth == 0){
                value = 0;//return valuation function call
            }
            else if (Board.checkState(aiPos)==1){
                value = 1000000;
            }
            else if (Board.checkState(playerPos)==1){
                value = -10000000;
            }
            
        }
        else if (maximizingPlayer){
            value = -Integer.MAX_VALUE;
            for(int i = 0; i<7;i++){
                if(Board.canPlay(i, mask)){
                    try{
                    long newMask = aiPos.makeMove(i, mask, playerPos);
                    value = Math.max(value,minimax(aiPos, playerPos, newMask, depth-1, false));
                    }
                    catch(ExitException exit){
                        return Integer.MAX_VALUE*exit.getExitCode();
                    }
                    
                }
            }
        }
        else{
            value = Integer.MAX_VALUE;
            for(int i = 0; i<7;i++){
                if(Board.canPlay(i, mask)){
                    try{
                    long newMask = playerPos.makeMove(i, mask, playerPos);
                    value = Math.min(value,minimax(aiPos, playerPos, newMask, depth-1, true));
                    }
                    catch(ExitException exit){
                        return Integer.MAX_VALUE*exit.getExitCode();
                    }
                    
                }
            }
        }
        return value;
    }
}
