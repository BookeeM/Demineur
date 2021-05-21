package demineur.ihm;

import java.awt.Font;
import java.io.IOException;

import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import demineur.ihm.frame.FrameDebut;
import demineur.utils.Settings;

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
		/*Création des précedents score / load*/
		final YamlFile save = new YamlFile("scores.yaml");

		try {
            if (!save.exists()) {
                save.createNewFile(true);
            }
			save.load();
			Settings.initScores(save);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FrameDebut fd = new FrameDebut();
		fd.setVisible(true);
	}
}
