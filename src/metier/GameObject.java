package src.metier;

import java.awt.image.BufferedImage;

public class GameObject 
{
    private BufferedImage img;

    private int x;
    private int y;
    private int width;
    private int height;

    public GameObject( BufferedImage img, int x, int y, int width, int height )
    {
        this.img    = img;
        this.x      = x;
        this.y      = y;
        this.width  = width;
        this.height = height;
    }

    public BufferedImage getImg() { return this.img; }
    public int getX()      { return this.x;      }
    public int getY()      { return this.y;      }
    public int getWidth()  { return this.width;  }
    public int getHeight() { return this.height; }
}
