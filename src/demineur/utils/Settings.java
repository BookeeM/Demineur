package demineur.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import demineur.ihm.Difficulte;


/**
 * @author Emilien G
 */
public class Settings 
{
	
	/** Permet de stocker dans un fichier yaml le score entré en paramètre
	 * 
	 * @param score : score à stocker
	 */
	public static void saveScore(Score score)
	{
		//On ne va stocker les scores que s'il sont positifs, donc que lorsque que l'on a gagné + sans triche.
		
		if(score.getScore() > 0)
		{
			final YamlFile save = new YamlFile("src/demineur/assets/saves/scores.yaml");
			try {
				save.load();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ArrayList<Score> scores = insertScore(score, save);
			writeScore(scores,save);
		}		
		//System.out.println(readScore(1).getScore()+" "+readScore(1).getTemps()+" "+readScore(1).getDif());
	}
	
	/** Permet d'écrire la liste des scores dans le fichier YAML
	 * 
	 * @param scores
	 * @param save
	 */
	public static void writeScore(ArrayList<Score> scores, YamlFile save)
	{
		try {
			save.load();
		} catch (InvalidConfigurationException | IOException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<scores.size();i++)
		{
			save.set(Integer.toString(i)+".temps", scores.get(i).getTemps());
			save.set(Integer.toString(i)+".dif", scores.get(i).getDif().getLabel());
			save.set(Integer.toString(i)+".score", scores.get(i).getScore());
		}
		
		try {
			save.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Permet de trier la liste des scores
	 * 
	 * @param score
	 * @param save
	 * @return scores
	 */
	public static ArrayList<Score> insertScore(Score score, YamlFile save)
	{
		ArrayList<Score> scores = getScores(save);
		scores.add(score);
		Collections.sort(scores);  

		return scores;
	}
	
	/**
	 * Retourne la liste contenant les différents scores dans le fichier yaml
	 * @param save
	 * @return
	 */
	public static ArrayList<Score> getScores(YamlFile save)
	{
		ArrayList<Score> scores = new ArrayList<Score>();
		
		try {
			save.load();
		} catch (InvalidConfigurationException | IOException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<getNumber(save);i++)
		{
			String pos = Integer.toString(i);
			int oldScore = save.getInt(pos+".score");
			int oldTemps = save.getInt(pos+".temps");
			Difficulte oldDif = Difficulte.fromString(save.getString(pos+".dif"));
			Score score = new Score(oldScore,oldTemps,oldDif);
			scores.add(score);
		}
		return scores;
	}
	
	/**
	 *  Permet de retourner le nombre de scores stockés !
	 * @param save
	 * @return nbScores 
	 */
	public static int getNumber(YamlFile save)
	{
		
		try {
			save.load();
		} catch (InvalidConfigurationException | IOException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		while(save.get(Integer.toString(i)) != null)
		{
			i++;
		}
		return i;
	}
	
	

	/** Permet de sauvegarder les options passées en paramètres.
	 * 
	 * @param volume
	 * @param largeur
	 * @param hauteur
	 * @param timer
	 */
	public static void saveOption(int volume, int largeur, int hauteur,int timer)
	{
		Properties p = new Properties();
		p.setProperty("volume", Integer .toString(volume ));
		p.setProperty("largeur", Integer.toString(largeur));
		p.setProperty("hauteur", Integer.toString(hauteur));
		p.setProperty("timeConstraint", Integer.toString(timer));
		
		FileWriter fw;
		
		try {
			fw = new FileWriter(new File(Settings.class.getResource("/demineur/assets/saves/options.txt").toURI()));
		
			p.store(fw, "Options");
			fw.flush();
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Retourne le temps que l'utilisateur a choisi au max pour finir sa partie
	 * 
	 * @return int : contrainte de temps
	 */
	public static int getTimeConstraint()
	{
		int a = 0;
		try {
			Properties p = new Properties();
			FileReader fr = new FileReader(new File(Settings.class.getResource("/demineur/assets/saves/options.txt").toURI()));
			p.load(fr);
		    a = Integer.parseInt(p.getProperty("timeConstraint"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;	
	}
	
	/** Retourne le volume que l'utilisateur a choisi
	 * 
	 * @return volume : int
	 */
	public static int getVolumeOption()
	{
		int a = 0;
		try {
			Properties p = new Properties();
			FileReader fr = new FileReader(new File(Settings.class.getResource("/demineur/assets/saves/options.txt").toURI()));
			p.load(fr);
		    a = Integer.parseInt(p.getProperty("volume"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;	
	}
	
	/** Retourne la largeur de la grille que l'utilisateur a choisi
	 * 
	 * @return largeur : int
	 */
	public static int getLargeurOption()
	{
		int a = 0;
		try {
			Properties p = new Properties();
			FileReader fr = new FileReader(new File(Settings.class.getResource("/demineur/assets/saves/options.txt").toURI()));
			p.load(fr);
		    a = Integer.parseInt(p.getProperty("largeur"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;	
	}
	
	/** Retourne la hauteur de la grille que l'utilisateur a choisi
	 * 
	 * @return hauteur : int
	 */
	public static int getHauteurOption()
	{
		int a = 0;
		try {
			Properties p = new Properties();
			FileReader fr = new FileReader(new File(Settings.class.getResource("/demineur/assets/saves/options.txt").toURI()));
			p.load(fr);
		    a = Integer.parseInt(p.getProperty("hauteur"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;	
	}
}
