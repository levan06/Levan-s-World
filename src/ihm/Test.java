package src.ihm;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Test extends JPanel {

    BufferedImage[] runFrames;
    int currentFrame = 0;
    int x = 100;
    int y = 200;

    public Test() 
    {
        runFrames = new BufferedImage[3];

        try {
            runFrames[0] = ImageIO.read(getClass().getResource("/knight_run/run_01.png"));
            runFrames[1] = ImageIO.read(getClass().getResource("/knight_run/run_02.png"));
            runFrames[2] = ImageIO.read(getClass().getResource("/knight_run/run_03.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Timer timer = new Timer(120, e -> {
            currentFrame = (currentFrame + 1) % runFrames.length;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(runFrames[currentFrame], x, y, 80, 80, null);
    }
}