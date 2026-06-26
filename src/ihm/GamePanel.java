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

    private Timer animationTimer;

    private int currentFrame;
    private int x;
    private int y;

    private boolean rightPressed = false;
    private boolean leftPressed  = false;
    private boolean shiftPressed = false;
    private boolean playerJumped = false;

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


        /* initialize the array of the player's state images*/
        this.moveFramesArr = this.ctrl.initBufferedArr();
        startAnimation();

        /**
         * Method to manage Keyboard clicks
         */
        this.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() != KeyEvent.VK_SPACE )
                {
                    if (e.getKeyCode() == KeyEvent.VK_D)     rightPressed = true;
                    if (e.getKeyCode() == KeyEvent.VK_Q)     leftPressed  = true;
                    if (e.getKeyCode() == KeyEvent.VK_SHIFT) shiftPressed = true;

                    updatePlayerState();
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    ctrl.setState( "jump" );
                    System.out.println("Space Pressed");
                    startAnimation();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) 
            {
                if( e.getKeyCode() != KeyEvent.VK_SPACE )
                {
                    if (e.getKeyCode() == KeyEvent.VK_D)     rightPressed = false;
                    if (e.getKeyCode() == KeyEvent.VK_Q)     leftPressed  = false;
                    if (e.getKeyCode() == KeyEvent.VK_SHIFT) shiftPressed = false;

                    updatePlayerState();
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    System.out.println("Space Released");
                } 
            }
        });
    }


    /**
     * Private Method called after each 
     * click on the keyboard
     */
    private void updatePlayerState()
    {
        String newState;
    
        if (this.rightPressed || this.leftPressed)
        {
            if (this.shiftPressed)
                newState = "run";
            else
                newState = "walk";
        }
        else
        {
            newState = "idle";
        }
    
        if (!this.ctrl.getState().equals(newState))
        {
            this.ctrl.setState(newState);
            startAnimation();
        }
    }

    /**
     * Private method called when the player
     * change direction or movement
     */
    private void startAnimation()
    {
        this.moveFramesArr = this.ctrl.initBufferedArr();
        this.currentFrame = 0;

        if( this.animationTimer != null )
            this.animationTimer.stop();

        /*Animation with delays using javax.swing.Timer*/
        this.animationTimer = new Timer(120, e -> 
        {
            /* Making sure currentFrame isn't out of array-s length */
            if( this.currentFrame == this.moveFramesArr.length - 1 )
            {
                /* if the player Jumpes we animate it one time and reset to idle */
                if( this.ctrl.getState().equals( "jump" ) )
                {
                    this.ctrl.setState( "idle" );
                    animationTimer.stop();
                    this.startAnimation();
                }
                
                this.currentFrame = 0;
            }

            // Verify if the player's state (movement)
            if( this.ctrl.getState().equals( "run" ) )
            {
                if( this.rightPressed && this.shiftPressed ) this.x += 10;
                if( this.leftPressed  && this.shiftPressed ) this.x -= 7;
            }
            else if( this.ctrl.getState().equals( "walk" ) )
            {
                if( this.rightPressed ) this.x += 3;
                if( this.leftPressed  ) this.x -= 3;
            }
            else if( this.ctrl.getState().equals( "jump" ) )
            {
                if( this.currentFrame >= ( this.moveFramesArr.length / 2 ) - 1 )
                {
                    this.y += 5; 
                }
                else
                {
                    this.y -= 5;
                }
            }

            this.currentFrame++;
            repaint();
        });
        this.animationTimer.start();
    }


    /**
     * Private helper method
     * @return List of GameObjet wich contains
     * img, x, y, width and height
     */
    private ArrayList<GameObject> drawEarth()
    {
        ArrayList<GameObject> lstGameObjects = new ArrayList<GameObject>();

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
        ArrayList<GameObject> lstEarth = this.drawEarth();
        
        for( Object obj : lstEarth )
        {
            GameObject gameObj = (GameObject) obj;
            g.drawImage( gameObj.getImg(), gameObj.getX(), gameObj.getY(), gameObj.getWidth(), gameObj.getHeight(), this );
        }


        /*=====================*/
        /* Painting the Player */
        /*=====================*/
        if( this.moveFramesArr[ this.currentFrame ] != null )
        {
            BufferedImage imgMove = this.moveFramesArr[ this.currentFrame ];
            
            /* If the player is out of the window */
            if( this.x <= 0   ) this.x = 0;   // (left side)
            if( this.x >= 640 ) this.x = 640; // (right side)

            g.drawImage( imgMove, this.x, this.y, 110, 110, this );
        }
    }
}