package src.ihm;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import src.Controleur;

public class GamePanel extends JPanel
{
    private Controleur    ctrl;

    private BufferedImage background;
    private BufferedImage[] runFrames;

    private int currentFrame;
    private int x = 100;
    private int y = 100;

    public GamePanel( Controleur ctrl )
    {
        this.setFocusable(true);
        this.ctrl = ctrl;
        this.runFrames = new BufferedImage[10];
        this.currentFrame = 0;

        try 
        {
            this.background = ImageIO.read(new File("./src/images/backgrounds/background_01.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * initialize the array of 
         * running player images
         */
        try
        {
            for( int i = 0; i < this.runFrames.length; i++ )
            {
                String numFile = String.format( "%02d", i + 1 );

                this.runFrames[i] = ImageIO.read(new File( 
                    "./src/images/knight_run/run_" + numFile + ".png" )
                );
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

        Timer timer = new Timer(120, e -> {
            this.currentFrame = ( this.currentFrame + 1 ) % this.runFrames.length;
            this.x += 5;
            repaint();
        });
        timer.start();

        /**
         * Method to verify wich
         * keyboard button was clicked
         */
        this.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    System.out.println("D pressed");
                }
            }
        });
    }

    @Override
    protected void paintComponent( Graphics g )
    {
        super.paintComponent(g);
        
        /* Painting the Background Image */
		if ( this.background != null ) 
            g.drawImage(this.background, 0, 0, getWidth(), getHeight(), this);

        /* Painting each Running image of the player */
        if( this.runFrames[ this.currentFrame ] != null )
        {
            BufferedImage imgRun = this.runFrames[ this.currentFrame ];
            g.drawImage( imgRun, this.x, this.y, 100, 100, this );
        }
    }
}