package demineur.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

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
	
	private static ArrayList<Score> scores;
	
	public static void saveScore(Score score)
	{
		//On ne va stocker les scores que s'il sont positifs, donc que lorsque que l'on a gagné + sans triche.
		
		if(score.getScore() > 0)
		{
			final YamlFile save = new YamlFile("scores.yaml");

			try {
	            if (!save.exists()) {
	                save.createNewFile(true);
	            }
				save.load();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			insertScore(score, save);
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
	public static void insertScore(Score score, YamlFile save)
	{
		
		scores = getScores();
		scores.add(score);
		Collections.sort(scores);  
	}
	
	/**
	 *  Retourne la position d'un score dans le classement
	 * @param score
	 * @return position : int
	 */
	public static int getPos(Score score)
	{
		int pos = 1;
		
		final YamlFile save = new YamlFile("scores.yaml");

		try {
			save.load();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		for(Score s : getScores())
		{	
			if(score != s) {
				pos++;
			} else {
				return pos;
			}
		}
		return pos;
	}
	
	/**
	 * Retourne la liste contenant les différents scores dans le fichier yaml
	 * @param save
	 * @return
	 */
	public static ArrayList<Score> initScores(YamlFile save)
	{
		scores = new ArrayList<Score>();
		
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
	
	public static ArrayList<Score> getScores()
	{
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
			FileOutputStream out = new FileOutputStream("options.txt");	
		
			p.store(out, "Options");
			out.flush();
			out.close();
			
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
			InputStream in = new FileInputStream("options.txt");
			p.load(in);
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
			InputStream in = new FileInputStream("options.txt");
			p.load(in);
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
			InputStream in = new FileInputStream("options.txt");
			p.load(in);
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
			InputStream in = new FileInputStream("options.txt");
			p.load(in);
		    a = Integer.parseInt(p.getProperty("hauteur"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;	
	}
}
