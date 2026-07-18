package src.metier;

import src.Controller;

public class Player
{
    private Controller ctrl;

    private int x;
    private int y;

    private String state;
    

    public Player( Controller ctrl )
    {
        this.ctrl  = ctrl;
        this.x     = 1050;
        this.y     = 480;
        this.state = "idle";
    }

    public int    getX()     { return this.x;     }
    public int    getY()     { return this.y;     }
    public String getState() { return this.state; }

    public void setState( String state ) { this.state = state; }
}