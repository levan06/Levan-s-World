package src.ihm;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import src.Controleur;

public class GamePanel extends JPanel
{
    private Controleur    ctrl;

    private BufferedImage background;
    private BufferedImage player;

    public GamePanel( Controleur ctrl )
    {
        this.ctrl = ctrl;

        try 
        {
            this.background = ImageIO.read(new File("./src/images/backgrounds/background_01.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try
        {
            this.player = ImageIO.read(getClass().getResource("/knight_run/Run_01.png"));
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent( Graphics g )
    {
        super.paintComponent(g);
        
		if ( this.background != null ) 
            g.drawImage(this.background, 0, 0, getWidth(), getHeight(), null);

        if( this.player != null )
            g.drawImage(this.player, 100, 100, 100, 100, null);
    }
}