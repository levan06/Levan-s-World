package src.ihm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import src.metier.GameObject;

public class DrawObjet 
{
    public DrawObjet()
    {

    }

    /**
     * Private helper method
     * @return List of GameObjet (earth) wich contains
     * img, x, y, width and height
     */
    public ArrayList<GameObject> drawEarth()
    {
        ArrayList<GameObject> lstGameObjects = new ArrayList<GameObject>();

        try 
        {
            BufferedImage imgEarth           = ImageIO.read( new File( "./src/images/tiles_objects/platform_35.png" ) );
            BufferedImage imgLowerEarth      = ImageIO.read( new File( "./src/images/tiles_objects/platform_30.png" ) );
            BufferedImage imgUpperLeftEarth  = ImageIO.read( new File( "./src/images/tiles_objects/platform_29.png" ) );
            BufferedImage imgUpperRightEarth = ImageIO.read( new File( "./src/images/tiles_objects/platform_31.png" ) );
            BufferedImage rightInclined      = ImageIO.read( new File( "./src/images/tiles_objects/platform_25.png" ) );
            BufferedImage leftInclined       = ImageIO.read( new File( "./src/images/tiles_objects/platform_27.png" ) );
            BufferedImage leftUpperCliff     = ImageIO.read( new File( "./src/images/tiles_objects/platform_32.png" ) );
            BufferedImage rightUppertCliff   = ImageIO.read( new File( "./src/images/tiles_objects/platform_34.png" ) );         
            BufferedImage leftLowerCliff     = ImageIO.read( new File( "./src/images/tiles_objects/platform_37.png" ) );
            BufferedImage rightLowertCliff   = ImageIO.read( new File( "./src/images/tiles_objects/platform_33.png" ) );

            // Upper Layer of earth
            lstGameObjects.add( new GameObject( imgUpperLeftEarth,     0, 570, 100, 100 ) );
            lstGameObjects.add( new GameObject( imgUpperRightEarth,  200, 570, 100, 100 ) );
            lstGameObjects.add( new GameObject( imgEarth,            100, 470, 100, 100 ) );
            lstGameObjects.add( new GameObject( imgEarth,            300, 570, 100, 100 ) );
            lstGameObjects.add( new GameObject( imgEarth,            700, 570, 100, 100 ) );
            lstGameObjects.add( new GameObject( imgEarth,            800, 570, 100, 100 ) );
            lstGameObjects.add( new GameObject( imgEarth,            900, 570, 100, 100 ) );
            lstGameObjects.add( new GameObject( imgEarth,           1000, 570, 100, 100 ) );
            lstGameObjects.add( new GameObject( imgEarth,           1100, 570, 100, 100 ) );
            lstGameObjects.add( new GameObject( leftUpperCliff,      400, 570, 100, 100 ) );
            lstGameObjects.add( new GameObject( rightUppertCliff,    600, 570, 100, 100 ) );

            // Lower layer of earth
            lstGameObjects.add( new GameObject( imgLowerEarth,   0, 655, 400, 200 ) );
            lstGameObjects.add( new GameObject( imgLowerEarth,   700, 655, 500, 200 ) );
            lstGameObjects.add( new GameObject( imgLowerEarth, 100, 555, 100, 100 ) );

            // Cliffs
            lstGameObjects.add( new GameObject( leftLowerCliff,   400, 660, 100, 110 ) );
            lstGameObjects.add( new GameObject( rightLowertCliff, 600, 660, 100, 110 ) );

            // Right inclined hill
            lstGameObjects.add( new GameObject( rightInclined,     0, 470, 100, 100 ) );
            lstGameObjects.add( new GameObject( leftInclined,    200, 470, 100, 100 ) );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return lstGameObjects;
    }


    /**
     * Private helper method
     * @return List of GameObjet (decor) wich contains
     * img, x, y, width and height
     */
    public ArrayList<GameObject> drawDecor()
    {
        ArrayList<GameObject> lstGameObjects = new ArrayList<GameObject>();

        try 
        {
            BufferedImage imgHouse = ImageIO.read( new File( "./src/images/decor/house.png" ) );

            lstGameObjects.add( new GameObject( imgHouse, 900, 371, 300, 200 ) );
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return lstGameObjects;
    }


}
