package src.metier;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import src.Controller;

public class PlayerMove 
{
    private Controller ctrl;

    private Player     player;

    private int currentFrame;

    public PlayerMove( Controller ctrl, Player player )
    {
        this.ctrl   = ctrl;
        this.player = player;
    }

    /**
     * 
     * @param state of the player
     * @return array of bufferedImages based on
     * the state of the player 
     */
    public BufferedImage[] initBufferedArr( String state )
    {
        BufferedImage[] moveFramesArr = new BufferedImage[10];

        try
        {
            for( int i = 0; i < moveFramesArr.length; i++ )
            {
                String numFile = String.format( "%02d", i + 1 );

                moveFramesArr[i] = ImageIO.read(new File( 
                    "./src/images/knight_" + state + "/" + state + "_" + numFile + ".png" )
                );
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

        return moveFramesArr;
    }
}
