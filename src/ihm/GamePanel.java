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
    private DrawObjet     draw;

    private BufferedImage   background;
    private BufferedImage[] moveFramesArr;

    private Timer animationTimer;

    private int currentFrame;
    private int x;
    private int y;

    private boolean rightPressed  = false;
    private boolean leftPressed   = false;
    private boolean shiftPressed  = false;
    private boolean isFacingRight = false;
    private boolean isClimbing    = false;
    
    public GamePanel( Controller ctrl )
    {
        this.setFocusable(true);
        this.ctrl = ctrl;
        this.draw = new DrawObjet();

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
                if( e.getKeyCode() != KeyEvent.VK_SPACE &&
                    e.getKeyCode() != KeyEvent.VK_F )
                {
                    if (e.getKeyCode() == KeyEvent.VK_D)
                    {
                        rightPressed  = true;
                        isFacingRight = true;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_Q)
                    {
                        leftPressed   = true;
                        isFacingRight = false;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_SHIFT) shiftPressed = true;

                    updatePlayerState();
                }

                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    ctrl.setState( "jump" );
                    startAnimation();
                }

                if( e.getKeyCode() == KeyEvent.VK_F )
                {
                    ctrl.setState( "attack" );
                    startAnimation();
                }

                if (e.getKeyCode() == KeyEvent.VK_Z)
                {
                    ctrl.setState( "idle" );
                    isClimbing = true;
                    startAnimation();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) 
            {
                if( e.getKeyCode() != KeyEvent.VK_SPACE &&
                    e.getKeyCode() != KeyEvent.VK_F )
                {
                    if (e.getKeyCode() == KeyEvent.VK_D)     rightPressed = false;
                    if (e.getKeyCode() == KeyEvent.VK_Q)     leftPressed  = false;
                    if (e.getKeyCode() == KeyEvent.VK_SHIFT) shiftPressed = false;

                    updatePlayerState();
                } 

                if( e.getKeyCode() == KeyEvent.VK_Z )
                {
                    isClimbing = false;
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
        this.animationTimer = new Timer(110, e -> 
        {
            /* Making sure currentFrame isn't out of array-s length */
            if( this.currentFrame == this.moveFramesArr.length - 1 )
            {
                /* if the player Jumpes or attacks we animate it one time and reset to idle */
                if( this.ctrl.getState().equals( "jump"   ) ||
                    this.ctrl.getState().equals( "attack" ) )
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
                if( this.rightPressed && this.shiftPressed ) this.x += 15;
                if( this.leftPressed  && this.shiftPressed ) this.x -= 15;
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
            else if( this.ctrl.getState().equals( "idle" ) && this.isClimbing )
            {
                this.y -= 5;
            }

            this.currentFrame++;
            repaint();
        });
        this.animationTimer.start();
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
        ArrayList<GameObject> lstEarth = this.draw.drawEarth();
        
        for( GameObject obj : lstEarth )
        {
            GameObject gameObj = obj;
            g.drawImage( gameObj.getImg(), gameObj.getX(), gameObj.getY(), gameObj.getWidth(), gameObj.getHeight(), this );
        }

        /*========================*/
        /* Painting the Platforms */
        /*========================*/
        ArrayList<GameObject> lstPlat = this.draw.drawPlatform();

        for( GameObject obj : lstPlat )
        {
            GameObject gameObj = obj;
            g.drawImage( gameObj.getImg(), gameObj.getX(), gameObj.getY(), gameObj.getWidth(), gameObj.getHeight(), this );
        }

        /*====================*/
        /* Painting the decor */
        /*====================*/
        ArrayList<GameObject> lstDecor = this.draw.drawDecor();
        
        for( GameObject obj : lstDecor )
        {
            GameObject gameObj = obj;
            g.drawImage( gameObj.getImg(), gameObj.getX(), gameObj.getY(), gameObj.getWidth(), gameObj.getHeight(), this );
        }


        /*=====================*/
        /* Painting the Player */
        /*=====================*/
        if( this.moveFramesArr[ this.currentFrame ] != null )
        {
            BufferedImage imgMove = this.moveFramesArr[ this.currentFrame ];
            
            /* If the player is out of the window */
            if( this.x <= 0    ) this.x = 0;    // (left side)
            if( this.x >= 1100 ) this.x = 1100; // (right side)

            if( this.isFacingRight )
                g.drawImage( imgMove, this.x, this.y, 110, 110, this );
            else
                g.drawImage( imgMove, this.x + 110, this.y, -110, 110, this );
        }
    }
}