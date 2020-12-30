
public class InstanPosEval {

    private static final int AI_THREE_IN_A_ROW_VALUE = 100;
    private static final int USER_THREE_IN_A_ROW_VALUE = 50;
    private static final int AI_TWO_IN_A_ROW_VALUE = 20;
    private static final int USER_TWO_IN_A_ROW_VALUE = 10;

public static int evaluatePosition (int [][] board){
    //using 1's for the ai (+) and 2 for the player (-)
    //check for vertical 3 in a row
    int score = 0;
    for (int i = 0; i <= 6; i++){
        for (int j = 0; j <= 3; j++){
            if (board[i][j] > 0 && board[i][j] == board[i][j+1] && board[i][j+1] == board[i][j+2]){
                if ((j > 0 && board[i][j-1] == 0) || (j <= 2 && board[i][j+3] == 0)){
                    if (board[i][j] == 1) {
                        score = score + AI_THREE_IN_A_ROW_VALUE;
                    }
                    else {
                        score = score - USER_THREE_IN_A_ROW_VALUE;
                    }
                }               
            }         
    }
}
    //check for horizontal 3 in a row
    for (int j = 0; j <= 5; j++){
        for (int i = 0; i <= 4; i++){
            if (board[i][j] > 0 && board[i][j] == board[i+1][j] && board[i+1][j] == board[i+2][j]){
                if ((i > 0 && board[i-1][j] == 0) || (i <= 3 && board[i+3][j] == 0)){
                    if (board[i][j] == 1) {
                        score = score + AI_THREE_IN_A_ROW_VALUE;
                    }
                    else {
                        score = score - USER_THREE_IN_A_ROW_VALUE;
                    }
                }             
            }        
    }
}
//check for diagonal 3 in a rows
for (int j = 0; j <= 3; j++){
    for (int i = 0; i <= 4; i++){
        if (board[i][j] > 0 && board[i][j] == board[i+1][j+1] && board[i+1][j+1] == board[i+2][j+2]){
            if ((i > 0 && j> 0 && board[i-1][j-1] == 0) || (i <= 3 && j <= 2 && board[i+3][j+3] == 0)){
                if (board[i][j] == 1) {
                    score = score + AI_THREE_IN_A_ROW_VALUE;
                }
                else {
                    score = score - USER_THREE_IN_A_ROW_VALUE;
                }
            }          
        }      
    }
}
    
    //check for vertical 2 in a row
    for (int i = 0; i <= 6; i++){
        for (int j = 0; j <= 4; j++){
            if (board[i][j] > 0 && board[i][j] == board[i][j+1]){
                if ((j > 0 && board[i][j-1] == 0) || (j <= 3 && board[i][j+2] == 0)){
                    if (board[i][j] == 1) {
                        score = score + AI_TWO_IN_A_ROW_VALUE;
                    }
                    else {
                        score = score - USER_TWO_IN_A_ROW_VALUE;
                    }
                }               
            }        
    }
}
    //check for horizontal 2 in a row
    for (int i = 0; i <= 5; i++){
        for (int j = 0; j <= 5; j++){
            if (board[i][j] > 0 && board[i][j] == board[i+1][j]){
                if ((i > 0 && board[i-1][j] == 0) || (i <= 4 && board[i+2][j] == 0)){
                    if (board[i][j] == 1) {
                        score = score + AI_TWO_IN_A_ROW_VALUE;
                    }
                    else {
                        score = score - USER_TWO_IN_A_ROW_VALUE;
                    }
                }            
            }         
    }
}
    //check for diagonal 2 in a row
    for (int i = 0; i <= 5; i++){
        for (int j = 0; j <= 4; j++){
            if (board[i][j] > 0 && board[i][j] == board[i+1][j+1]){
                if ((i > 0 && j > 0 && board[i-1][j-1] == 0) || 
                (i <= 4 && j <= 3 && board[i+2][j+2] == 0)){
                    if (board[i][j] == 1) {
                        score = score + AI_TWO_IN_A_ROW_VALUE;
                    }
                    else {
                        score = score - USER_TWO_IN_A_ROW_VALUE;
                    }
                }              
            }     
    }
}
    return score;
}
}