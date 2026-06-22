package src;

import src.ihm.GameFrame;

public class Controleur
{
    private GameFrame ihm;

    public Controleur()
    {
        this.ihm = new GameFrame( this );
    }

    public static void main(String[] args) {
        new Controleur();
    }
}