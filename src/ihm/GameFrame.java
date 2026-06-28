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
        this.setSize(1200, 800);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel(this.ctrl);
        this.add(panel);

        this.setVisible(true);

        SwingUtilities.invokeLater(() -> {
            panel.requestFocusInWindow();
        });
    }
}