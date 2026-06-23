package src;

import java.awt.image.BufferedImage;
import src.ihm.GameFrame;
import src.metier.Player;
import src.metier.PlayerMove;

public class Controller
{
    private GameFrame ihm;

    private Player     player;
    private PlayerMove playerMove;
    
    private String state;

    /* Constructor */
    public Controller()
    {
        this.player     = new Player    ( this );
        this.playerMove = new PlayerMove( this, player );
        this.state      = this.player.getState();
        this.ihm        = new GameFrame ( this );
    }

    public BufferedImage[] initBufferedArr() { return this.playerMove.initBufferedArr( this.state ); }

    /* ============*/
    /*   Getters   */
    /* ============*/
    public String getState()   { return this.player.getState(); }
    public int    getPlayerX() { return this.player.getX();     }
    public int    getPlayerY() { return this.player.getY();     }

    /* ============*/
    /*   Setters   */
    /* ============*/
    public void setState( String state ) { this.player.setState( state ); }



    public static void main(String[] args) {
        new Controller();
    }
}