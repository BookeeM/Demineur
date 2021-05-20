package demineur.ihm;

public enum Difficulte 
{
	DEBUTANT("DÉBUTANT",1),
	AMATEUR ("AMATEUR" ,2),
	MOYEN   ("MOYEN"   ,3),
	HABILE  ("HABILE"  ,4),
	EXPERT  ("EXPERT"  ,5);

	private String label;
	private int lvl;
	
	Difficulte(String label, int lvl) 
	{
		this.label = label;
		this.lvl = lvl;
	}
	

	/**
	 * Retourne le niveau correspondant à la difficulté
	 * 
	 * @return int
	 */
	public int getLvl()
	{
		return lvl;
	}
	
	/**
	 * Retourne le nom associé à la difficulté
	 * 
	 * @return string
	 */
	public String getLabel()
	{
		return this.label;
	}
	
	/**
	 * Retourne la difficulté associé à un label
	 * 
	 * @return Difficulte
	 */
	public static Difficulte fromString(String dif)
	{
		switch(dif)
		{
			case "DÉBUTANT":
				return DEBUTANT;
			case "AMATEUR":
				return AMATEUR;
			case "MOYEN":
				return MOYEN;
			case "HABILE":
				return HABILE;
			case "EXPERT":
				return EXPERT;
		}
		return DEBUTANT;
	}
}
