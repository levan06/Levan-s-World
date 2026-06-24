package src.ihm;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import src.Controller;
import src.metier.GameObject;

public class GamePanel extends JPanel
{
    private Controller    ctrl;

    private BufferedImage   background;
    private BufferedImage[] moveFramesArr;

    private int currentFrame;
    private int x;
    private int y;

    public GamePanel( Controller ctrl )
    {
        this.setFocusable(true);
        this.ctrl = ctrl;

        /*=======================*/
        /* Create the components */
        /*=======================*/
        this.x = this.ctrl.getPlayerX();
        this.y = this.ctrl.getPlayerY();

        this.moveFramesArr = new BufferedImage[10];
        this.currentFrame  = 0;

        try 
        {
            this.background = ImageIO.read(new File("./src/images/backgrounds/background_01.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * initialize the array of the player's
         * state images
         */
        this.moveFramesArr = this.ctrl.initBufferedArr();

        /**
         * Animation with delays using javax.swing.Timer
         */
        Timer timer = new Timer(120, e -> {
            this.currentFrame = ( this.currentFrame + 1 ) % this.moveFramesArr.length;

            // Verify if the player is running or walking
            if( this.ctrl.getState().equals( "run" ) )
                this.x += 7;
            else if( this.ctrl.getState().equals( "walk" ) )
                this.x += 3;
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

    /**
     * Private helper method
     * @return List of GameObjet wich contains
     * img, x, y, width and height
     */
    private ArrayList<GameObject> drawEarth()
    {
        ArrayList lstGameObjects = new ArrayList<GameObject>();

        try 
        {
            BufferedImage imgEarth   = ImageIO.read(new File("./src/images/tiles_objects/platform_26.png"));

            lstGameObjects.add( new GameObject( imgEarth,   0, 650, 250, 100 ) );
            lstGameObjects.add( new GameObject( imgEarth, 520, 650, 250, 100 ) );
            lstGameObjects.add( new GameObject( imgEarth, 250, 650, 270, 100 ) );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return lstGameObjects;
    }

    @Override
    protected void paintComponent( Graphics g )
    {
        super.paintComponent(g);
        
        /* Painting the Background Image */
		if ( this.background != null ) 
            g.drawImage(this.background, 0, 0, getWidth(), getHeight(), this);

        /*====================*/
        /* Painting the earth */
        /*====================*/
        ArrayList lstEarth = this.drawEarth();
        
        for( Object obj : lstEarth )
        {
            GameObject gameObj = (GameObject) obj;
            g.drawImage( gameObj.getImg(), gameObj.getX(), gameObj.getY(), gameObj.getWidth(), gameObj.getHeight(), this );
        }

        
        /* Painting each Running image of the player */
        if( this.moveFramesArr[ this.currentFrame ] != null )
        {
            BufferedImage imgMove = this.moveFramesArr[ this.currentFrame ];
            g.drawImage( imgMove, this.x, this.y, 100, 100, this );
        }
    }
}