public class Test {


   
public static int evaluatePosition (int [][] board){
    //using 1's for the ai (+) and 2 for the player (-)
    //check for vertical 3 in a row
    int score = 0;
    for (int i = 0; i <= 6; i++){
        for (int j = 0; j <= 3; j++){
            if (board[i][j] > 0 && board[i][j] == board[i][j+1] && board[i][j+1] == board[i][j+2]){
                if (j > 0 && board[i][j-1] == 0){
                    if (board[i][j] == 1) {
                        score = score + 100;
                    }
                    else {
                        score = score - 50;
                    }
                }
                else if (j <= 2 && board[i][j+3] == 0){
                    if (board[i][j] == 1) {
                        score = score + 100;
                    }
                    else {
                        score = score - 50;
                    }
                }
                
            }
            
    }
}
    //check for horizontal 3 in a row
    for (int j = 0; j <= 5; j++){
        for (int i = 0; i <= 4; i++){
            if (board[i][j] > 0 && board[i][j] == board[i+1][j] && board[i+1][j] == board[i+2][j]){
                if (i > 0 && board[i-1][j] == 0){
                    if (board[i][j] == 1) {
                        score = score + 100;
                    }
                    else {
                        score = score - 50;
                    }
                }
                else if (i <= 3 && board[i+3][j] == 0){
                    if (board[i][j] == 1) {
                        score = score + 100;
                    }
                    else {
                        score = score - 50;
                    }
                }
                
            }
            
    }
}
//check for diagonal 3 in a rows
for (int j = 0; j <= 3; j++){
    for (int i = 0; i <= 4; i++){
        if (board[i][j] > 0 && board[i][j] == board[i+1][j+1] && board[i+1][j+1] == board[i+2][j+2]){
            if (i > 0 && j> 0 && board[i-1][j-1] == 0){
                if (board[i][j] == 1) {
                    score = score + 100;
                }
                else {
                    score = score - 50;
                }
            }
            else if (i <= 3 && j <= 2 && board[i+3][j+3] == 0){
                if (board[i][j] == 1) {
                    score = score + 100;
                }
                else {
                    score = score - 50;
                }
            }
            
        }
        
}
}
    
    //check for vertical 2 in a row
    for (int i = 0; i <= 6; i++){
        for (int j = 0; j <= 4; j++){
            if (board[i][j] > 0 && board[i][j] == board[i][j+1]){
                if (j > 0 && board[i][j-1] == 0){
                    if (board[i][j] == 1) {
                        score = score + 20;
                    }
                    else {
                        score = score - 10;
                    }
                }
                else if (j <= 3 && board[i][j+2] == 0){
                    if (board[i][j] == 1) {
                        score = score + 20;
                    }
                    else {
                        score = score - 10;
                    }
                }
                
            }
            
    }
}
    //check for horizontal 2 in a row
    for (int i = 0; i <= 5; i++){
        for (int j = 0; j <= 5; j++){
            if (board[i][j] > 0 && board[i][j] == board[i+1][j]){
                if (i > 0 && board[i-1][j] == 0){
                    if (board[i][j] == 1) {
                        score = score + 20;
                    }
                    else {
                        score = score - 10;
                    }
                }
                else if (i <= 4 && board[i+2][j] == 0){
                    if (board[i][j] == 1) {
                        score = score + 20;
                    }
                    else {
                        score = score - 10;
                    }
                }
                
            }
            
    }
}
    //check for diagonal 2 in a row
    for (int i = 0; i <= 5; i++){
        for (int j = 0; j <= 4; j++){
            if (board[i][j] > 0 && board[i][j] == board[i+1][j+1]){
                if (i > 0 && j > 0 && board[i-1][j-1] == 0){
                    if (board[i][j] == 1) {
                        score = score + 20;
                    }
                    else {
                        score = score - 10;
                    }
                }
                else if (i <= 4 && j <= 3 && board[i+2][j+2] == 0){
                    if (board[i][j] == 1) {
                        score = score + 20;
                    }
                    else {
                        score = score - 10;
                    }
                }
                
            }
            
    }
}
    return score;
}
}