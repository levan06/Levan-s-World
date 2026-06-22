package src.ihm;

import javax.swing.JFrame;
import src.Controleur;

public class GameFrame extends JFrame
{
    private Controleur ctrl;
    
    public GameFrame( Controleur ctrl )
    {
        this.ctrl = ctrl;

        this.setTitle( "Levan's World !" );
        this.setSize(750, 750);

        GamePanel panel = new GamePanel( this.ctrl );
        this.add( panel );


        this.setVisible( true );
        this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}