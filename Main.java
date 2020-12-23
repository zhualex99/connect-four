import javax.swing.*;
import java.awt.*;
public class Main{
    public static void main (String[] args){
        System.out.println("Hello Will");
    }
}


class gui{
      public static void main(String args[]){
           JFrame frame = new JFrame("Connect Four");
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setSize(1000,1000);

           //Create the bottom panel
           JPanel panel = new JPanel(); // the panel is not visible in output
           JButton confirm = new JButton("Confirm");
           JButton reset = new JButton("Reset");
           panel.add(confirm);
           panel.add(reset);
           frame.getContentPane().add(BorderLayout.SOUTH, panel);

           frame.setVisible(true);       

     }
}
