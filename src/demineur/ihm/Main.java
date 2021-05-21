package demineur.ihm;

import java.awt.Font;

import demineur.ihm.frame.FrameDebut;

/**
 * @author Emilien G
 */
public class Main
{

	
	public static Font font = new Font(Font.SANS_SERIF, 20, 20);
	/*
	 * Méthode de lancement du programme
	 */
	public static void main(String[] args)
	{
		FrameDebut fd = new FrameDebut();
		fd.setVisible(true);
	}
}
