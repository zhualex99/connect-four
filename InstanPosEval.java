public class InstanPosEval {

    private static final int AI_THREE_IN_A_ROW_VALUE = 100;
    private static final int USER_THREE_IN_A_ROW_VALUE = -50;
    private static final int AI_TWO_IN_A_ROW_VALUE = 20;
    private static final int USER_TWO_IN_A_ROW_VALUE = -10;

    public static int smartThreeInARow (int space1, int space2, int space3, int space4){
        //checks if all of the 4 spaces are either occupied by the user or empty
        if (((space1 == 2 || space1 == 0) && (space2 == 2 || space2 == 0) &&
        (space3 == 2 || space3 == 0) && (space4 == 2 || space4 == 0)) && 
        (space1 + space2 + space3 + space4 == 6)){
                return USER_THREE_IN_A_ROW_VALUE;
        }
        else if ((space1 <= 1 && space2 <= 1 && space3 <= 1 && space4 <= 1) && 
        (space1 + space2 + space3 + space4 == 3)){
                return AI_THREE_IN_A_ROW_VALUE;
        }
        else {
            return 0;
        }
    }

    public static int smartTwoInARow (int space1, int space2, int space3){
        //checks if all of the 3 spaces are either occupied by the user or empty
        if (((space1 == 2 || space1 == 0) && (space2 == 2 || space2 == 0) &&
        (space3 == 2 || space3 == 0)) && (space1 + space2 + space3 == 4)){
                return USER_TWO_IN_A_ROW_VALUE;
        }
        else if ((space1 <= 1 && space2 <= 1 && space3 <= 1) && 
        (space1 + space2 + space3 == 2)){
                return AI_TWO_IN_A_ROW_VALUE;
        }
        else {
            return 0;
        }
    }

public static int evaluatePosition (int [][] board){
    //using 1's for the ai (+) and 2 for the player (-)
    //check for vertical 3 in a row
    int score = 0;
    for (int i = 0; i <= 6; i++){
        for (int j = 0; j <= 3; j++){            
                if (j > 0) {
                    score = score + smartThreeInARow (board[i][j], board[i][j+1], 
                    board[i][j+2], board[i][j-1]);
                }        
                else if (j <= 2){
                    score = score + smartThreeInARow (board[i][j], board[i][j+1], 
                    board[i][j+2], board[i][j+3]);
                }        
    }
}
    //check for horizontal 3 in a row
    for (int j = 0; j <= 5; j++){
        for (int i = 0; i <= 4; i++){
                if (i > 0) {
                    score = score + smartThreeInARow (board[i][j], board[i+1][j], 
                    board[i+2][j], board[i-1][j]);
                }
                else if (i <= 3) {
                    score = score + smartThreeInARow (board[i][j], board[i+1][j], 
                    board[i+2][j], board[i+3][j]);
                }               
    }
}
//check for diagonal 3 in a rows
for (int j = 0; j <= 3; j++){
    for (int i = 0; i <= 4; i++){
            if (i > 0 && j> 0) {
                score = score + smartThreeInARow (board[i+1][j+1], board[i+2][j+2], 
                board[i][j], board[i-1][j-1]);
            }
            else if (i <= 3 && j <= 2 && board[i+3][j+3] == 0) {
                score = score + smartThreeInARow (board[i+1][j+1], board[i+2][j+2], 
                board[i][j], board[i+3][j+3]);
            }      
    }
}
    
    //check for vertical 2 in a row
    for (int i = 0; i <= 6; i++){
        for (int j = 0; j <= 4; j++){
                if (j > 0) {
                    score = score + smartTwoInARow (board[i][j], board[i][j+1], board[i][j-1]);
                }
                else if (j <= 3) {
                    score = score + smartTwoInARow (board[i][j], board[i][j+1], board[i][j+2]);
                }
            }        
    }
    //check for horizontal 2 in a row
    for (int i = 0; i <= 5; i++){
        for (int j = 0; j <= 5; j++){
                if (i > 0) {
                    score = score + smartTwoInARow (board[i][j], board[i+1][j], board[i-1][j]);
                }  
                else if (i <= 4) {
                    score = score + smartTwoInARow (board[i][j], board[i+1][j], board[i+2][j]);
                }         
            }         
    }
    //check for diagonal 2 in a row
    for (int i = 0; i <= 5; i++){
        for (int j = 0; j <= 4; j++){
                if (i > 0 && j > 0) {
                    score = score + smartTwoInARow (board[i][j], board[i+1][j+1], board[i-1][j-1]);
                }
                else if (i <= 4 && j <= 3) {
                    score = score + smartTwoInARow (board[i][j], board[i+1][j+1], board[i+2][j+2]);
                }
            }     
    }
    return score;
}
}