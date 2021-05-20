package demineur.ihm.frame.component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Settings 
{
	
	public static void saveScore(int score)
	{
		
	}
	
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
