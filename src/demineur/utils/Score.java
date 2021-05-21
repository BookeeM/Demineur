package demineur.utils;

import demineur.ihm.Difficulte;
/**
 * @author Emilien G
 */
public class Score implements Comparable<Score>
{
	
	private int score;
	private int temps;
	private Difficulte dif;
	
	public Score(int score, int temps, Difficulte dif)
	{
		this.score = score;
		this.temps = temps;
		this.dif = dif;
	}
	
	/** Retourne le score associé à l'objet Score
	 * 
 	 * @return score
	 */
	public int getScore()
	{
		return this.score;
	}
	
	/** retourne le temps associé à l'objet Score
	 * 
	 * @return temps
	 */
	public int getTemps()
	{
		return this.temps;
	}
	
	/** Retourne la difficulté associée à l'objet Score
	 * 
	 * @return  dif
	 */
	public Difficulte getDif()
	{
		return this.dif;
	}
	
	/** Fonction permettant à l'objet Score d'être trié par la classe Collections
	 * @return int 
	 */
	@Override
	public int compareTo(Score i) {
		if(getScore() < i.getScore()) return -1;
		if(getScore() > i.getScore()) return 1;
		return 0;
	}
}
