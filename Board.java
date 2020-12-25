
public class Board {
    private long pos = 0;

    /**
     * prints the binary representation of the player position
     * @param pos position to be printed
     */
    public static void printBits(long pos){
        String bits = Long.toBinaryString(pos);
        System.out.println("Start: "+ bits);
        
        char[][] b = new char [7][7];
        for(int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++){
                if(((i*7)+j)<bits.length()){
                    b[j][i] = bits.charAt (bits.length()-((i*7)+j)-1);
                }
                else {
                    b[j][i] = '0';
                }
                

            }

        }
        for(int i = 6 ; i >= 0; i--){
            for(int j = 0; j < 7; j++){
                System.out.print(b[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * adds a player piece in the the column specified
     * @param col   the column for the piece to be dropped
     * @param mask  the binary mask of the two players
     * @param opp   the postion of the opponent
     * @return      the new mask
     * @throws ExitException    thrown when a result has been achieved (win/loss/draw)
     */
    public long makeMove(int col, long mask, Board opp) throws ExitException{
        //System.out.println(Long.toBinaryString(mask));
        //opp.setBoard(pos^mask);
        long newMask =  mask | (long)(mask + (Math.pow(2,(col*7))));
        pos = opp.getPos()^newMask;
        checkState();
        
        return newMask;
    } 

    
    /**
     * setter for the board position
     * @param position  the position of the player
     */
    public void setBoard(long position){
        pos = position;
    }

    /**
     * gets the position
     * @return  the position of the player
     */
    public long getPos (){
        return pos;
    }

    /**
     * checks if there is a result on the board
     * @throws ExitException thrown when a result is on the board (win/loss/draw)
     */
    private void checkState() throws ExitException{
        //TODO: add draw case
        long m = pos & (pos >> 7);
        System.out.println("Here" + (m& (m >> 14)));
        if ((m & (m >> 14)) > 0){
            
            throw new ExitException(ExitException.WIN);
        }

        m = pos & (pos >> 6);
        if ((m & (m >> 12))>0){
            throw new ExitException(ExitException.WIN);
        }

        m = pos & (pos >> 8);
        if ((m & (m >> 16))>0){
            throw new ExitException(ExitException.WIN);
        }

        m = pos & (pos >> 1);
        if ((m & (m >> 2))>0){
            throw new ExitException(ExitException.WIN);
        }
        
        
    }
    

    
}