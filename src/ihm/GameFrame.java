package src.ihm;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import src.Controller;

public class GameFrame extends JFrame
{
    private Controller ctrl;
    
    public GameFrame(Controller ctrl)
    {
        this.ctrl = ctrl;

        this.setTitle("Levan's World !");
        this.setSize(750, 750);

        this.setResizable(false);
        this.setLocation( 700, 100);;
        //this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel(this.ctrl);
        this.add(panel);

        this.setVisible(true);

        SwingUtilities.invokeLater(() -> {
            panel.requestFocusInWindow();
        });
    }
}