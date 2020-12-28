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
    private Board player = new Board();
    private Board ai = new Board();
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
        }

    }

    

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseCol = (int)Math.floor((double)mouseX/((double)WIDTH/7));
        System.out.println(mouseCol);
        for (int i = 0; i < board[mouseCol].length; i++){
            if (board[mouseCol][i] == 0){
                board[mouseCol][i] = playerTurn? 1:2;
                playerTurn = !playerTurn;
                break;
            }
        }
        try{
        mask = playerTurn? player.makeMove(mouseCol, mask, ai) : ai.makeMove(mouseCol, mask, player);
        
        
        Board.printBits(mask);
        System.out.print("Yellow: ");
        Board.printBits(player.getPos());
        System.out.print("Red: ");
        Board.printBits(ai.getPos());
        System.out.println();
        game.repaint();
        }
        catch(ExitException exit){
            game.paintImmediately(game.getBounds());
            game.removeMouseListener(this);
            Object[] options = {"Continue", "Exit", "New Game"};
            
            if(exit.getExitCode() == ExitException.WIN){
                
                int n = JOptionPane.showOptionDialog(null, (playerTurn? "Player" : "AI") + " WINS", "Exit Condition Met", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
                System.out.println(n);
                if(n == 1){//exit
                    System.exit(0);
                }
                else if (n == 2){
                    Main.main(new String[]{});
                }
            }
            else {
                int n = JOptionPane.showOptionDialog(null, "DRAW", "Exit Condition Met", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
                if(n == 1){//exit
                    System.exit(0);
                }
            }
        }
        
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    
    
}
