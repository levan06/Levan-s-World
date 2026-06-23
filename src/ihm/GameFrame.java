package src.ihm;

import javax.swing.JFrame;
import src.Controller;

public class GameFrame extends JFrame
{
    private Controller ctrl;
    
    public GameFrame( Controller ctrl )
    {
        this.ctrl = ctrl;

        this.setTitle( "Levan's World !" );
        this.setSize(750, 750);

        GamePanel panel = new GamePanel( this.ctrl );
        this.add( panel );
        panel.requestFocusInWindow();


        this.setVisible( true );
        this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}