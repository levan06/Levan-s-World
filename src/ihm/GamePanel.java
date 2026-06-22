package src.ihm;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import src.Controleur;

public class GamePanel extends JPanel
{
    private Controleur    ctrl;
    private BufferedImage background;

    public GamePanel( Controleur ctrl )
    {
        this.ctrl = ctrl;

        try 
        {
            background = ImageIO.read(new File("./src/images/backgrounds/background_01.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent( Graphics g )
    {
        super.paintComponent(g);

        Image img = Toolkit.getDefaultToolkit().getImage("./images/backgrounds/background_01.png");
		if (background != null) 
        {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        }
    }
}