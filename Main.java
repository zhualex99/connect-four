import javax.swing.JFrame;
import javax.swing.JOptionPane;


import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

public class Main implements MouseListener {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 600;
    public static final int RADIUS = WIDTH/7;
    private int mouseX;
    private int mouseCol;
    private long player = 0;
    private long ai = 0;
    private int[][] board = new int[7][6];
    private long mask = 0;
    private Drawing game = new Drawing();
    private boolean playerTurn = false;
    
    

    public static void main(String[] args) {
        Main main = new Main();
        main.init();
        

    }

    /**
     * inits all gui components
     */
    private void init() {
        JFrame frame = new JFrame("Connect 4");
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(game);
        game.addMouseListener(this);
        frame.setSize(WIDTH, HEIGHT+30);
        
        
    }

    class Drawing extends JComponent {
        /**
         * paint method to draw the board
         */
        public void paint(Graphics g) {
            for (int i = 0; i < 7; i++) {
                g.drawLine(i * (100), 0, i * (100), Main.HEIGHT);
                
            }
            for(int i = 0; i< 6; i++){
                g.drawLine(0, i * (100), Main.WIDTH, i * (100));
            }
            for(int j = 0; j < 7; j++){
                for (int i = 0; i<6 ; i++){
                    if (board[j][i] == 1){
                        //draw player1 piece
                        g.setColor(Color.RED);
                        g.fillOval(100 * j, Main.HEIGHT - (100 * (i + 1)), RADIUS, RADIUS);
                    }
                    else if (board [j][i] == 2){
                        //draw player2 piece
                        g.setColor(Color.YELLOW);
                        g.fillOval(100*j,Main.HEIGHT-(100*(i+1)),RADIUS,RADIUS);
                    }
                }
            }
            //System.out.println(Test.evaluatePosition(board));
        }

    }

    

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseCol = (int)Math.floor((double)mouseX/((double)WIDTH/7));
        //System.out.println(mouseCol);
        makeMove(mouseCol);
        int[] result = AI.minimax(ai, player,Integer.MIN_VALUE, Integer.MAX_VALUE, mask, 10, true);
        
        makeMove(result[1]);
        System.out.println("value: "+ result[0]);
        System.out.println("move: " + result[1]);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}

    public void makeMove (int col){
        for (int i = 0; i < board[col].length; i++){
            if (board[col][i] == 0){
                board[col][i] = playerTurn? 1:2;
                playerTurn = !playerTurn;
                break;
            }
        }
        System.out.println("current value: " + Test.evaluatePosition(board));
        
        //mask = playerTurn? player.makeMove(mouseCol, mask, ai) : ai.makeMove(mouseCol, mask, player);

        if(playerTurn){
            long[] newBoard = Board.makeMove(col, mask, player, ai);
            player = newBoard[1];
            mask = newBoard[0];
        }
        else{
            long[] newBoard = Board.makeMove(col, mask, ai, player);
            ai = newBoard[1];
            mask = newBoard[0];
        }

        if(Board.checkState(ai) == 1 || Board.checkState(player) == 1){
            System.out.println("win");
        }
        
        //System.out.println(ai);
        //System.out.println(player);
        //System.out.println(AI.isTerminal(player));
        // Board.printBits(mask);
        // System.out.print("Yellow: ");
        //Board.printBits(ai);
        // System.out.print("Red: ");
        //Board.printBits(player);
        // System.out.println();
        game.paintImmediately(game.getBounds());
        
        // catch(ExitException exit){
        //     game.paintImmediately(game.getBounds());
        //     game.removeMouseListener(this);
        //     Object[] options = {"Continue", "Exit", "New Game"};
            
        //     if(exit.getExitCode() == ExitException.WIN){
                
        //         int n = JOptionPane.showOptionDialog(null, (playerTurn? "Player" : "AI") + " WINS", "Exit Condition Met", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
        //         //System.out.println(n);
        //         if(n == 1){//exit
        //             System.exit(0);
        //         }
        //         else if (n == 2){
        //             Main.main(new String[]{});
        //         }
        //     }
        //     else {
        //         int n = JOptionPane.showOptionDialog(null, "DRAW", "Exit Condition Met", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
        //         if(n == 1){//exit
        //             System.exit(0);
        //         }
        //     }
        // }
        
        
    }
    
    
}
